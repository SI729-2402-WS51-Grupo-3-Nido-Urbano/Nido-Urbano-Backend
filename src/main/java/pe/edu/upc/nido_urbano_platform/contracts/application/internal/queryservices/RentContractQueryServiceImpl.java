package pe.edu.upc.nido_urbano_platform.contracts.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.RentContractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentContractQueryServiceImpl implements RentalContractQueryService {

    private final RentContractRepository rentContractRepository;

    @Autowired
    public RentContractQueryServiceImpl(RentContractRepository rentContractRepository) {
        this.rentContractRepository = rentContractRepository;
    }

    @Override
    public Optional<RentalContract> getContractDetails(Long contractId) {
        return rentContractRepository.findById(contractId);
    }

    @Override
    public List<RentalContract> listContractsByProperty(Long propertyId) {
        return rentContractRepository.findByPropertyId(propertyId).stream().toList();
    }

    @Override
    public Optional<RentalContract> findContractsByTenant(Long tenantId) {
        return rentContractRepository.findById(tenantId);
    }

    @Override
    public List<RentalContract> findContractsByLandlord(Long landlordId) {
        return rentContractRepository.findByLandlordId(landlordId);
    }

    @Override
    public Object findById(Long contractId) {
        return null;
    }
}

