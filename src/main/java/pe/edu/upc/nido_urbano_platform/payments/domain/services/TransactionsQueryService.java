package pe.edu.upc.nido_urbano_platform.payments.domain.services;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TransactionsQueryService {
    List<Transactions> handle(GetAllTransactionsQuery query);
    Optional<Transactions> handle(GetTransactionByIdQuery query);
    List<Transactions> handle(GetTransactionsByPaymentIdQuery query);
}
