package pe.edu.upc.nido_urbano_platform.payments.domain.model.commands;

import java.math.BigDecimal;

public record CreateTransactionCommand(BigDecimal transactionAmount, Long payments) {
}
