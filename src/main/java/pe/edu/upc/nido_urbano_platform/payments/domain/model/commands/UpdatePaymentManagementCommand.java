package pe.edu.upc.nido_urbano_platform.payments.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentSchedule;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentStatus;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

public record UpdatePaymentManagementCommand(Long paymentManagementId, Long contractId, PaymentSchedule paymentSchedule,
                                             Date nextPayment, PaymentStatus paymentStatus, BigDecimal totalAmount,
                                             boolean reminderSent, PaymentType paymentType) {
}
