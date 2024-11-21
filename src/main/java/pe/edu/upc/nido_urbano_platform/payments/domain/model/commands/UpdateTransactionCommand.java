package pe.edu.upc.nido_urbano_platform.payments.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.TransactionStatus;

import java.math.BigDecimal;
import java.util.Date;

public record UpdateTransactionCommand(Long transactionId, Date transactionDate, BigDecimal transactionAmount,
                                       TransactionStatus transactionStatus, Long paymentId) {
}
