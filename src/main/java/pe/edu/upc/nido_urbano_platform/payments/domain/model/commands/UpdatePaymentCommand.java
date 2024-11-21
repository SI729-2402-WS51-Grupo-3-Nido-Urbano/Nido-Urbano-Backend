package pe.edu.upc.nido_urbano_platform.payments.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public record UpdatePaymentCommand(Long paymentId, Long contractId, Date paymentDate, BigDecimal paymentAmount,
                                   BigDecimal remainingAmount, PaymentStatus paymentStatus, Long paymentManagementId) {
}
