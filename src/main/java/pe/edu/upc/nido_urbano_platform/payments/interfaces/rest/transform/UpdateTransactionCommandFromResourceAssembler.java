package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdateTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.TransactionResource;

public class UpdateTransactionCommandFromResourceAssembler {
    public static UpdateTransactionCommand toCommandFromResource(Long transactionId, TransactionResource resource) {
        return new UpdateTransactionCommand(transactionId, resource.transactionDate(), resource.transactionAmount(),
                resource.transactionStatus(), resource.paymentId());
    }
}
