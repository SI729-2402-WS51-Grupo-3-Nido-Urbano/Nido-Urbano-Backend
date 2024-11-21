package pe.edu.upc.nido_urbano_platform.payments.application.internal.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreateTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeleteTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdateTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;
import pe.edu.upc.nido_urbano_platform.payments.domain.services.TransactionsCommandService;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentManagementRepository;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.TransactionRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionsCommandServiceImpl implements TransactionsCommandService {
    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentManagementRepository paymentManagementRepository;

    public TransactionsCommandServiceImpl(TransactionRepository transactionRepository,
                                          PaymentRepository paymentRepository,
                                          PaymentManagementRepository paymentManagementRepository) {
        this.transactionRepository = transactionRepository;
        this.paymentRepository = paymentRepository;
        this.paymentManagementRepository = paymentManagementRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateTransactionCommand command) {
        Date transactionDate = new Date();
        var optionalPayments = this.paymentRepository.findById(command.payments());
        if (optionalPayments.isEmpty()) {
            throw new IllegalArgumentException("Payment not with id " + command.payments() + "found");
        }

        if (this.transactionRepository.existsByTransactionDateAndPayment(transactionDate, optionalPayments.get())) {
            throw new IllegalArgumentException("Payment with transaction date " + transactionDate + "and " +
                    "id " + command.payments() + "already exists");
        }

        var payments = optionalPayments.get();
        var paymentManagement = optionalPayments.get().getPaymentManagement();
        if (paymentManagement == null) {
            throw new IllegalArgumentException("Payment management not found");
        }

        var transaction = new Transactions(command, optionalPayments.get());

        try {
            this.transactionRepository.save(transaction);
            payments.updateRemainingAmount();
            payments.updatePaymentStatus();
            this.paymentRepository.save(payments);
            paymentManagement.updatePaymentStatus();
            paymentManagement.calculateNextPaymentDate();
            paymentManagement.updateTotalAmount();
            this.paymentManagementRepository.save(paymentManagement);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving transaction " + e.getMessage());
        }

        return transaction.getTransactionId();
    }

    @Override
    public Optional<Transactions> handle(UpdateTransactionCommand command) {
        Date transactionDate = new Date();
        var optionalPayments = this.paymentRepository.findById(command.paymentId());
        var transactionId = command.transactionId();
        if (optionalPayments.isEmpty()) {
            throw new IllegalArgumentException("Payment not with id " + command.paymentId() + "found");
        }

        if (this.transactionRepository.existsByTransactionDateAndPaymentAndTransactionIdIsNot(transactionDate,
                optionalPayments.get(), transactionId)) {
            throw new IllegalArgumentException("Transaction with transaction date " + command.paymentId() + " and"
            + " payment id " + command.paymentId() + " already exists");
        }

        if (!this.transactionRepository.existsById(transactionId)) {
            throw new IllegalArgumentException("Payment with id " + transactionId + " does not exist");
        }

        var payments = optionalPayments.get();
        var paymentManagement = optionalPayments.get().getPaymentManagement();

        var transactionToUpdate = this.transactionRepository.findById(transactionId).get();
        transactionToUpdate.updateInformation(command.transactionDate(), command.transactionAmount(),
                command.transactionStatus());

        try {
            this.transactionRepository.save(transactionToUpdate);
            payments.updateRemainingAmount();
            payments.updatePaymentStatus();
            this.paymentRepository.save(payments);
            paymentManagement.updatePaymentStatus();
            paymentManagement.calculateNextPaymentDate();
            paymentManagement.updateTotalAmount();
            this.paymentManagementRepository.save(paymentManagement);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving transaction " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public void handle(DeleteTransactionCommand command) {
        if (!this.transactionRepository.existsById(command.transactionId())) {
            throw new IllegalArgumentException("Transaction with id " + command.transactionId() + " does not exist");
        }

        var transactionToDelete = this.transactionRepository.findById(command.transactionId()).get();
        var payment = this.paymentRepository.findById(transactionToDelete.getPayment().getId());
        if (payment.isEmpty()) {
            throw new IllegalArgumentException("Payment not with id " + transactionToDelete.getPayment().getId() + "found");
        }

        var paymentManagement = this.paymentManagementRepository.findById(payment.get().getPaymentManagement().getId());
        if (paymentManagement.isEmpty()) {
            throw new IllegalArgumentException("Payment management with id: " +
                    payment.get().getPaymentManagement().getId() + "not found");
        }

        try {
            this.transactionRepository.deleteById(command.transactionId());
            payment.get().updateRemainingAmount();
            payment.get().updatePaymentStatus();
            this.paymentRepository.save(payment.get());
            paymentManagement.get().updatePaymentStatus();
            paymentManagement.get().calculateNextPaymentDate();
            paymentManagement.get().updateTotalAmount();
            this.paymentManagementRepository.save(paymentManagement.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting transaction " + e.getMessage());
        }
    }
}
