package pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.PropertyId;

import java.util.Optional;

@Repository
public interface RentContractRepository extends JpaRepository<RentalContract, Long> {
    // Find contract by property ID
    Optional<RentalContract> findByPropertyId(PropertyId propertyId);

    Optional<RentalContract> findByLandlordId(LandlordId landlordId);
}
