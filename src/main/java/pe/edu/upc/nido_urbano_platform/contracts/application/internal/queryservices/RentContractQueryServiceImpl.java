package pe.edu.upc.nido_urbano_platform.contracts.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByLandLordIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByUserIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractByIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.RentContractRepository;

import java.util.Optional;

@Service
public class RentContractQueryServiceImpl implements RentalContractQueryService {

    private final RentContractRepository rentContractRepository;

    @Autowired
    public RentContractQueryServiceImpl(RentContractRepository rentContractRepository) {
        this.rentContractRepository = rentContractRepository;
    }

    @Override
    public Optional<RentalContract> handle(GetAllContractsByUserIdQuery query) {
        return this.rentContractRepository.findById(query.userId().userId());
    }

    @Override
    public Optional<RentalContract> handle(GetAllContractsByLandLordIdQuery query) {
        return this.rentContractRepository.findByLandlordId(query.landlordId());
    }

    @Override
    public Optional<RentalContract> handle(GetAllContractsByPropertyIdQuery query) {
        return this.rentContractRepository.findByPropertyId(query.propertyId());
    }

    @Override
    public Optional<RentalContract> handle(GetContractByIdQuery query) {
        return this.rentContractRepository.findById(query.contractId());
    }
}

