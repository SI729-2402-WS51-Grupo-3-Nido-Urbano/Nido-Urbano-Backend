package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdatePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentManagementResource;

public class UpdatePaymentManagementCommandFromResourceAssembler {
    public static UpdatePaymentManagementCommand toCommandFromResource(Long paymentManagementId,
                                                                       PaymentManagementResource resource) {
        return new UpdatePaymentManagementCommand(paymentManagementId, resource.contractId(), resource.schedule(),
                resource.nextPayment(), resource.paymentStatus(), resource.totalAmount(), resource.reminderSent(),
                resource.type());
    }
}
