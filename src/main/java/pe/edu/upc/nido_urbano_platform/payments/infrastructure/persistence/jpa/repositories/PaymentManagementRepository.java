package pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;

import java.util.Optional;

@Repository
public interface PaymentManagementRepository extends JpaRepository<PaymentManagement, Long> {
    boolean existsByContractId(ContractId contractId);
    boolean existsByContractIdAndIdIsNot(ContractId contractId, Long id);
    Optional<PaymentManagement> findByContractId(ContractId contractId);
}
