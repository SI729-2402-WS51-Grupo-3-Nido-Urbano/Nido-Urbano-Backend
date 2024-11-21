package pe.edu.upc.nido_urbano_platform.payments.domain.services;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.*;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;

import java.util.Optional;

public interface TransactionsCommandService {
    Long handle(CreateTransactionCommand command);
    Optional<Transactions> handle(UpdateTransactionCommand command);
    void handle(DeleteTransactionCommand command);
}
