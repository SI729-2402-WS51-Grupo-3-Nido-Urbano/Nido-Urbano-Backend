package pe.edu.upc.nido_urbano_platform.payments.application.internal.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeletePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdatePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;
import pe.edu.upc.nido_urbano_platform.payments.domain.services.PaymentCommandService;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentManagementRepository;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;
    private final PaymentManagementRepository paymentManagementRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, PaymentManagementRepository paymentManagementRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentManagementRepository = paymentManagementRepository;
    }

    @Override
    @Transactional
    public Long handle(CreatePaymentCommand command) {
        var contractId = command.contractId();
        var paymentDate = command.paymentDay();
        if (this.paymentRepository.existsByContractIdAndPaymentDate(new ContractId(contractId), paymentDate)) {
            throw new IllegalArgumentException("Payment with Contract id " + contractId + " and same PaymentDate " +
                    paymentDate + " already exists");
        }

        var paymentManagement = this.paymentManagementRepository.findById(command.paymentManagementId());
        if (paymentManagement.isEmpty()) {
            throw new IllegalArgumentException("Payment management with id " + command.paymentManagementId() + " not found");
        }

        var payment = new Payments(command, paymentManagement.get());
        try {
            payment.setPaymentDate(paymentManagement.get().getNextPayment());
            this.paymentRepository.save(payment);
            paymentManagement.get().updatePaymentStatus();
            paymentManagement.get().calculateNextPaymentDate();
            paymentManagement.get().updateTotalAmount();
            this.paymentManagementRepository.save(paymentManagement.get());

        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving payment " + e.getMessage());
        }
        return payment.getId();
    }

    @Override
    public Optional<Payments> handle(UpdatePaymentCommand command) {
        var contractId = command.contractId();
        var paymentDate = command.paymentDate();
        var paymentId = command.paymentId();
        if (this.paymentRepository.existsByContractIdAndPaymentDateAndIdIsNot(new ContractId(contractId), paymentDate,
                paymentId)) {
            throw new IllegalArgumentException("Payment with contract id " + contractId + " and same payment " +
                    "date already exists");
        }

        if (!this.paymentRepository.existsById(paymentId)) {
            throw new IllegalArgumentException("Payment with id " + paymentId + " does not exist");
        }

        var paymentManagement = this.paymentManagementRepository.findById(command.paymentManagementId());
        if (paymentManagement.isEmpty()) {
            throw new IllegalArgumentException("Payment management with id " + command.paymentManagementId() + " not found");
        }

        var paymentToUpdate = this.paymentRepository.findById(paymentId).get();
        paymentToUpdate.updateInformation(command.contractId(), command.paymentDate(), command.paymentAmount(),
                command.remainingAmount(), command.paymentStatus());
        try {
            paymentToUpdate.setPaymentDate(paymentManagement.get().getNextPayment());
            var updatedPayment = this.paymentRepository.save(paymentToUpdate);
            paymentManagement.get().updatePaymentStatus();
            paymentManagement.get().calculateNextPaymentDate();
            paymentManagement.get().updateTotalAmount();
            this.paymentManagementRepository.save(paymentManagement.get());
            return Optional.of(updatedPayment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePaymentCommand command) {
        if (!this.paymentRepository.existsById(command.paymentId())) {
            throw new IllegalArgumentException("Payment with id " + command.paymentId() + " does not exist");
        }

        var paymentToDelete = this.paymentRepository.findById(command.paymentId()).get();
        var paymentManagement = this.paymentManagementRepository.findById(paymentToDelete.getPaymentManagement().getId());
        if (paymentManagement.isEmpty()) {
            throw new IllegalArgumentException("Payment management with id " + paymentToDelete.getPaymentManagement().getId()
                    + " not found");
        }

        try {
            paymentToDelete.setPaymentDate(paymentManagement.get().getNextPayment());
            this.paymentRepository.deleteById(command.paymentId());
            paymentManagement.get().updatePaymentStatus();
            paymentManagement.get().calculateNextPaymentDate();
            paymentManagement.get().updateTotalAmount();
            this.paymentManagementRepository.save(paymentManagement.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting payment: " + e.getMessage());
        }
    }
}
