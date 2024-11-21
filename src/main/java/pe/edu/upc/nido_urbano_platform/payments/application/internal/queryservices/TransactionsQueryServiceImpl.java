package pe.edu.upc.nido_urbano_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllTransactionsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetTransactionByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetTransactionsByPaymentIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.services.TransactionsQueryService;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsQueryServiceImpl implements TransactionsQueryService {
    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;

    public TransactionsQueryServiceImpl(TransactionRepository transactionRepository, PaymentRepository paymentRepository) {
        this.transactionRepository = transactionRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Transactions> handle(GetAllTransactionsQuery query) {
        return this.transactionRepository.findAll();
    }

    @Override
    public Optional<Transactions> handle(GetTransactionByIdQuery query) {
        return this.transactionRepository.findById(query.transactionId());
    }

    @Override
    public List<Transactions> handle(GetTransactionsByPaymentIdQuery query) {
        var optionalPayment = this.paymentRepository.findById(query.paymentId());
        if (optionalPayment.isEmpty()) {
            throw new IllegalArgumentException("Payment with id " + query.paymentId() + "not found");
        }
        return this.transactionRepository.findByPayment(optionalPayment.get());
    }
}
