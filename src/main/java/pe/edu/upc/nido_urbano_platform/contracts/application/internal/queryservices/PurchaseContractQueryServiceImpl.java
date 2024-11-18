package pe.edu.upc.nido_urbano_platform.contracts.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.PurchaseContractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseContractQueryServiceImpl implements PurchaseContractQueryService {

    private final PurchaseContractRepository purchaseContractRepository;

    @Autowired
    public PurchaseContractQueryServiceImpl(PurchaseContractRepository purchaseContractRepository) {
        this.purchaseContractRepository = purchaseContractRepository;
    }


    @Override
    public Optional<PurchaseContract> getContractDetails(Long contractId) {
        return purchaseContractRepository.findById(contractId);
    }

    @Override
    public List<PurchaseContract> listContractsByProperty(Long propertyId) {
        return purchaseContractRepository.findByPropertyId(propertyId).stream().toList();
    }

    @Override
    public Optional<PurchaseContract> findContractsByTenant(Long tenantId) {
        return purchaseContractRepository.findById(tenantId);
    }

    @Override
    public List<PurchaseContract> findContractsByLandlord(Long landlordId) {
        return purchaseContractRepository.findByLandlordId(landlordId);
    }

    @Override
    public Object findById(Long contractId) {
        return null;
    }
}
