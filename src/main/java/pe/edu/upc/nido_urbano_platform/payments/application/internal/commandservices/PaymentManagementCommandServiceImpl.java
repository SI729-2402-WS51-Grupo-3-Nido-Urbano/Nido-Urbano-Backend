package pe.edu.upc.nido_urbano_platform.payments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeletePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdatePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;
import pe.edu.upc.nido_urbano_platform.payments.domain.services.PaymentManagementCommandService;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentManagementRepository;

import java.util.Optional;

@Service
public class PaymentManagementCommandServiceImpl implements PaymentManagementCommandService {
    private final PaymentManagementRepository paymentManagementRepository;

    public PaymentManagementCommandServiceImpl(PaymentManagementRepository paymentManagementRepository) {
        this.paymentManagementRepository = paymentManagementRepository;
    }

    @Override
    public Long handle(CreatePaymentManagementCommand command) {
        var contractId = command.contractId();
        if (this.paymentManagementRepository.existsByContractId(new ContractId(contractId))) {
            throw new IllegalArgumentException("Contract with id " + contractId + " already exists");
        }
        var paymentManagement = new PaymentManagement(command);
        try {
            this.paymentManagementRepository.save(paymentManagement);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving payment management " + e.getMessage());
        }
        return paymentManagement.getId();
    }

    @Override
    public Optional<PaymentManagement> handle(UpdatePaymentManagementCommand command) {
        var paymentManagementId = command.paymentManagementId();
        var contractId = command.contractId();
        if(this.paymentManagementRepository.existsByContractIdAndIdIsNot(new ContractId(contractId), paymentManagementId)) {
            throw new IllegalArgumentException("Contract with id " + contractId + " already exists");
        }
        if (!this.paymentManagementRepository.existsById(paymentManagementId)) {
            throw new IllegalArgumentException("Payment Management with id " + paymentManagementId + " does not exists");
        }

        var paymentManagementToUpdate = this.paymentManagementRepository.findById(paymentManagementId).get();
        paymentManagementToUpdate.updateInformation(command.contractId(), command.paymentSchedule(), command.nextPayment(),
                command.paymentStatus(), command.totalAmount(), command.reminderSent(), command.paymentType());

        try {
            var updatedPaymentManagement = this.paymentManagementRepository.save(paymentManagementToUpdate);
            return Optional.of(updatedPaymentManagement);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating payment management " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePaymentManagementCommand command) {
        if (!this.paymentManagementRepository.existsById(command.paymentManagementId())) {
            throw new IllegalArgumentException("Payment Management with id " + command.paymentManagementId() +
                    " does not exists");
        }

        try {
            this.paymentManagementRepository.deleteById(command.paymentManagementId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting payment management " + e.getMessage());
        }
    }
}
