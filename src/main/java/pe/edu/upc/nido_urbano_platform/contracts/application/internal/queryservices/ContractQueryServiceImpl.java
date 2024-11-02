package pe.edu.upc.nido_urbano_platform.contracts.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.ContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.ContractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContractQueryServiceImpl implements ContractQueryService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractQueryServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Optional<Contract> getContractDetails(Long contractId) {
        return contractRepository.findById(contractId);
    }

    @Override
    public List<Contract> listContractsByProperty(Long propertyId) {
        return contractRepository.findByPropertyId(propertyId).stream().toList();
    }

    @Override
    public List<Contract> findContractsByTenant(Long tenantId) {
        return contractRepository.findByTenantId(tenantId);
    }

    @Override
    public List<Contract> findContractsByLandlord(Long landlordId) {
        return contractRepository.findByLandlordId(landlordId);
    }
}

