package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.Date;

public record CreatePaymentsResource(Long contractId, Date paymentDate, BigDecimal paymentAmount, Long paymentManagementId) {
}
