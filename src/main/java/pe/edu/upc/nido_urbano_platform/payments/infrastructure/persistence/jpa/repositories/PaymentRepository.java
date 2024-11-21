package pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {
    boolean existsByContractIdAndPaymentDate(ContractId contractId, Date paymentDate);
    boolean existsByContractIdAndPaymentDateAndIdIsNot(ContractId contractId, Date paymentDate, Long id);
    List<Payments> findByPaymentManagement(PaymentManagement paymentManagement);
}
