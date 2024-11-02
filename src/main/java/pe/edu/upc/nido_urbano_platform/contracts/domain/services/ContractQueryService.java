package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractQueryService {
    Optional<Contract> getContractDetails(Long contractId);
    List<Contract> listContractsByProperty(Long propertyId);
    List<Contract> findContractsByTenant(Long tenantId);
    List<Contract> findContractsByLandlord(Long landlordId);
}
