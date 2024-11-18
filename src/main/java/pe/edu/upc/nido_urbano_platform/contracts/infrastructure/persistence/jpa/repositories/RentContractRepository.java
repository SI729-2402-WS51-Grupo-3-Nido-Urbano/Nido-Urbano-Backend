package pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;

import java.util.List;

@Repository
public interface RentContractRepository extends JpaRepository<RentalContract, Long> {
    // Find contract by property ID
    List<RentalContract> findByPropertyId(Long propertyId);

    List<RentalContract> findByLandlordId(Long landlordId);
}
