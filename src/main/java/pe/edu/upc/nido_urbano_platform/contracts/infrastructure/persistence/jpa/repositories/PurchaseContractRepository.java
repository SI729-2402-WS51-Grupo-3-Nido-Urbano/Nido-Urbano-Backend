package pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId;

import java.util.Optional;

@Repository
public interface PurchaseContractRepository extends JpaRepository<PurchaseContract, Long> {

    // Find contract by landlord ID
    //List<PurchaseContract> findByLandlordId(Long landlordId);

    // Find contract by property ID
    Optional<PurchaseContract> findByPropertyId(long propertyId);

    Optional<PurchaseContract> findByLandlordId(LandlordId landlordId);
}
