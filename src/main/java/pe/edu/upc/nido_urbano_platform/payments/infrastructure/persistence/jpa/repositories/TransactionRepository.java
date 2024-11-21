package pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    boolean existsByTransactionDateAndPayment(Date transactionDate, Payments payments);
    boolean existsByTransactionDateAndPaymentAndTransactionIdIsNot(Date transactionDate, Payments payments,
                                                                   Long transactionId);
    List<Transactions> findByPayment(Payments payments);
}
