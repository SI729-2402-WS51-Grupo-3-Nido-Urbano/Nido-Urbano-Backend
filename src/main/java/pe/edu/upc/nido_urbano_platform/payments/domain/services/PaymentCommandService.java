package pe.edu.upc.nido_urbano_platform.payments.domain.services;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeletePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdatePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Long handle(CreatePaymentCommand command);
    Optional<Payments> handle(UpdatePaymentCommand command);
    void handle(DeletePaymentCommand command);
}
