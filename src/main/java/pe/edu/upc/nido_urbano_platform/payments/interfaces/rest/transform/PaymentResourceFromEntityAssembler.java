package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentsResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.TransactionResource;

import java.util.List;

public class PaymentResourceFromEntityAssembler {
    public static PaymentsResource toResourceFromEntity(Payments entity) {
        List<TransactionResource> transactionResources = entity.getTransactions().stream()
                .map(transaction -> new TransactionResource(
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionStatus(),
                        transaction.getPayment().getId()
                ))
                .toList();

        return new PaymentsResource(entity.getId(), entity.getContractId().contractId(), entity.getPaymentDate(),
                entity.getPaymentAmount(), entity.getRemainingAmount(), entity.getPaymentStatus(),
                entity.getPaymentManagement().getId(), transactionResources);
    }
}
