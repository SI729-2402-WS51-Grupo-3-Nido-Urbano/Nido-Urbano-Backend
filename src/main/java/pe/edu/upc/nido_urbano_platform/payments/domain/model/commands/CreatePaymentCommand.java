package pe.edu.upc.nido_urbano_platform.payments.domain.model.commands;

import java.math.BigDecimal;
import java.util.Date;

public record CreatePaymentCommand(Long contractId, Date paymentDay, BigDecimal paymentAmount, Long paymentManagementId) {
}
