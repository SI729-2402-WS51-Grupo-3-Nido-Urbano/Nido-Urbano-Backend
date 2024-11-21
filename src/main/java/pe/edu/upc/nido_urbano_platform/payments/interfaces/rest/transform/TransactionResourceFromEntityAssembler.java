package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.TransactionResource;

public class TransactionResourceFromEntityAssembler {
    public static TransactionResource toResourceFromEntity(Transactions entity) {
        return new TransactionResource(entity.getTransactionId(), entity.getTransactionDate(),
                entity.getTransactionAmount(), entity.getTransactionStatus(), entity.getPayment().getId());
    }
}
