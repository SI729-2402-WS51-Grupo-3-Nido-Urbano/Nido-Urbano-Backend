package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentSchedule;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentStatus;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PaymentManagementResource(Long paymentManagementId, Long contractId, Date nextPayment,
                                        PaymentSchedule schedule, PaymentStatus paymentStatus, BigDecimal totalAmount,
                                        boolean reminderSent, BigDecimal firstPayment, PaymentType type,
                                        List<PaymentsResource> paymentsResources) {
}
