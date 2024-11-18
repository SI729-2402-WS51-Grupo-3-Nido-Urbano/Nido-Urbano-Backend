package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;

import java.util.List;
import java.util.Optional;

public interface RentalContractQueryService {
    Optional<RentalContract> getContractDetails(Long contractId);
    List<RentalContract> listContractsByProperty(Long propertyId);
    Optional<RentalContract> findContractsByTenant(Long tenantId);
    List<RentalContract> findContractsByLandlord(Long landlordId);

    Object findById(Long contractId);
}
