package pe.edu.upc.nido_urbano_platform.housing_management.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetAllHousesQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByHouseCodeQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByReservationIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.services.HouseQueryService;
import pe.edu.upc.nido_urbano_platform.housing_management.infrastructure.persistence.jpa.repositories.HouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HouseQueryServiceImpl implements HouseQueryService {

    private final HouseRepository houseRepository;

    public HouseQueryServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> handle(GetAllHousesQuery query) {
        return this.houseRepository.findAll();
    }

    @Override
    public Optional<House> handle(GetHouseByIdQuery query) {
        return this.houseRepository.findById(query.houseId());
    }

    @Override
    public Optional<House> handle(GetHouseByHouseCodeQuery query) {
        return this.houseRepository.findByHouseCode(query.houseCode());
    }

    @Override
    public Optional<House> handle(GetHouseByReservationIdQuery query) {
        return this.houseRepository.findByReservationId(query.reservationId());
    }
}
