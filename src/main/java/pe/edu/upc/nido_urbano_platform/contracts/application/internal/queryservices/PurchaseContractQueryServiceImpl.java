package pe.edu.upc.nido_urbano_platform.contracts.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractIdByPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByLandLordIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByUserIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractByIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.PurchaseContractRepository;

import java.util.Optional;

@Service
public class PurchaseContractQueryServiceImpl implements PurchaseContractQueryService {

    private final PurchaseContractRepository purchaseContractRepository;

    @Autowired
    public PurchaseContractQueryServiceImpl(PurchaseContractRepository purchaseContractRepository) {
        this.purchaseContractRepository = purchaseContractRepository;
    }


    @Override
    public Optional<PurchaseContract> handle(GetAllContractsByUserIdQuery query) {
        return this.purchaseContractRepository.findById(query.userId().userId());
    }

    @Override
    public Optional<PurchaseContract> handle(GetAllContractsByLandLordIdQuery query) {
        return this.purchaseContractRepository.findByLandlordId(query.landlordId());
    }

    @Override
    public Optional<PurchaseContract> handle(GetContractIdByPropertyIdQuery query) {
        return this.purchaseContractRepository.findByPropertyId(query.propertyId());
    }

    @Override
    public Optional<PurchaseContract> handle(GetContractByIdQuery query) {
        return this.purchaseContractRepository.findById(query.contractId());
    }
}
