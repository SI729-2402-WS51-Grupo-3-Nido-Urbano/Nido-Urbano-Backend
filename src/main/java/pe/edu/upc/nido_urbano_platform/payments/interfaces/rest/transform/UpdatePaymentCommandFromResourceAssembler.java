package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdatePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentsResource;

public class UpdatePaymentCommandFromResourceAssembler {
    public static UpdatePaymentCommand toCommandFromResource(Long paymentId, PaymentsResource resource) {
        return new UpdatePaymentCommand(paymentId, resource.contractId(), resource.paymentDate(),
                resource.paymentAmount(), resource.remainingAmount(), resource.paymentStatus(),
                resource.paymentManagementId());
    }
}
