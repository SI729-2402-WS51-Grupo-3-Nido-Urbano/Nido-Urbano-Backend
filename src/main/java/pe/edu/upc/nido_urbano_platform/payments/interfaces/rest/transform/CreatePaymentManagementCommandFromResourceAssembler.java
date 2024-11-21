package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.CreatePaymentManagementResource;

public class CreatePaymentManagementCommandFromResourceAssembler {
    public static CreatePaymentManagementCommand toCommandFromResource(CreatePaymentManagementResource resource) {
        return new CreatePaymentManagementCommand(resource.contractId(), resource.schedule(), resource.nextPayment(),
                resource.totalAmount(), resource.type());
    }
}
