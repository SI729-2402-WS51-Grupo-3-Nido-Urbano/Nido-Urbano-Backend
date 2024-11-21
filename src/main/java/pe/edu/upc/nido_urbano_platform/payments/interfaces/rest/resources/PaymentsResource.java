package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PaymentsResource(Long paymentId, Long contractId, Date paymentDate, BigDecimal paymentAmount,
                               BigDecimal remainingAmount, PaymentStatus paymentStatus, Long paymentManagementId,
                               List<TransactionResource> transactionResources) {
}
