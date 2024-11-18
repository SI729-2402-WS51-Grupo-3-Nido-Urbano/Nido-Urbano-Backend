package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;

import java.util.List;
import java.util.Optional;

public interface PurchaseContractQueryService {
    Optional<PurchaseContract> getContractDetails(Long contractId);
    List<PurchaseContract> listContractsByProperty(Long propertyId);
    Optional<PurchaseContract> findContractsByTenant(Long tenantId);
    List<PurchaseContract> findContractsByLandlord(Long landlordId);

    Object findById(Long contractId);
}
