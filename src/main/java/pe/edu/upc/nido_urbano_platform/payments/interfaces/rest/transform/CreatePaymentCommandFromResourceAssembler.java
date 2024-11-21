package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.CreatePaymentsResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentsResource resource) {
        return new CreatePaymentCommand(resource.contractId(), resource.paymentDate(), resource.paymentAmount(),
                resource.paymentManagementId());
    }
}
