package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentManagementResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentsResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.TransactionResource;

import java.util.List;

public class PaymentManagementResourceFromEntityAssembler {
    public static PaymentManagementResource toResourceFromEntity(PaymentManagement entity) {
        // Convertimos los Payments en PaymentResource
        List<PaymentsResource> paymentResources = entity.getPayments().stream()
                .map(payment -> new PaymentsResource(
                        payment.getId(),
                        payment.getContractId().contractId(),
                        payment.getPaymentDate(),
                        payment.getPaymentAmount(),
                        payment.getRemainingAmount(),
                        payment.getPaymentStatus(),
                        payment.getPaymentManagement().getId(),
                        // Convertimos las transacciones del pago a TransactionResource
                        payment.getTransactions().stream()
                                .map(transaction -> new TransactionResource(
                                        transaction.getTransactionId(),
                                        transaction.getTransactionDate(),
                                        transaction.getTransactionAmount(),
                                        transaction.getTransactionStatus(),
                                        payment.getId() // Relación con el Payment
                                ))
                                .toList() // Convertir la lista de transacciones
                ))
                .toList();  // Usar collect en vez de toList() si hay compatibilidad con versiones previas

        return new PaymentManagementResource(entity.getId(), entity.getContractId().contractId(),
                entity.getNextPayment(), entity.getPaymentSchedule(), entity.getPaymentStatus(), entity.getTotalAmount(),
                entity.getReminderSent(), entity.getFirstPayment(), entity.getPaymentType(), paymentResources);
    }
}