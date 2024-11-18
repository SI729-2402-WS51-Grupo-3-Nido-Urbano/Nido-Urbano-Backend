package pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseContractRepository extends JpaRepository<PurchaseContract, Long> {
    // Check if a contract exists by property ID
    //boolean existsByPropertyId(Long propertyId);

    // Find contract by tenant ID
    //List<PurchaseContract> findByTenantId(Long tenantId);

    // Find contract by landlord ID
    //List<PurchaseContract> findByLandlordId(Long landlordId);

    // Find contract by property ID
    List<PurchaseContract> findByPropertyId(Long propertyId);

    List<PurchaseContract> findByLandlordId(Long landlordId);
}
