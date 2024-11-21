package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources;

import java.math.BigDecimal;

public record CreateTransactionResource(BigDecimal transactionAmount, Long paymentId) {
}
