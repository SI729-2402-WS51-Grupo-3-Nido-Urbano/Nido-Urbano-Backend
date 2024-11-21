package pe.edu.upc.nido_urbano_platform.payments.domain.services;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeletePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdatePaymentManagementCommand;

import java.util.Optional;

public interface PaymentManagementCommandService {
    Long handle(CreatePaymentManagementCommand command);
    Optional<PaymentManagement> handle(UpdatePaymentManagementCommand command);
    void handle(DeletePaymentManagementCommand command);
}
