package pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    // Check if a contract exists by property ID
    boolean existsByPropertyId(Long propertyId);

    // Find contract by tenant ID
    List<Contract> findByTenantId(Long tenantId);

    // Find contract by landlord ID
    List<Contract> findByLandlordId(Long landlordId);

    // Find contract by property ID
    Optional<Contract> findByPropertyId(Long propertyId);

}
