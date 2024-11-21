package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentSchedule;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentType;

import java.math.BigDecimal;
import java.util.Date;

public record CreatePaymentManagementResource(Long contractId, PaymentSchedule schedule, Date nextPayment,
                                              BigDecimal totalAmount, PaymentType type) {
}
