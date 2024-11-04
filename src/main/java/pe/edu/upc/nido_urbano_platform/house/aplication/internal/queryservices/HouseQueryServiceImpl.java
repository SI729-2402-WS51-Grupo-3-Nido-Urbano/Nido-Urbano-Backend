package pe.edu.upc.nido_urbano_platform.house.aplication.internal.queryservices;

import pe.edu.upc.nido_urbano_platform.house.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetAllHousesQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByHouseModalQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByIdQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByUserPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.services.HouseQueryService;
import pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories.HouseRepository;

import java.util.List;
import java.util.Optional;

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
    public List<House> handle(GetHouseByHouseModalQuery query) {
        return this.houseRepository.findByHouseModal(query.houseModal().toString());
    }

    @Override
    public Optional<House> handle(GetHouseByIdQuery query) {
        return this.houseRepository.findById(query.houseId());
    }

    @Override
    public Optional<House> handle(GetHouseByUserPropertyIdQuery query) {
        return this.houseRepository.findByUserPropertyId(query.userPropertyId());
    }
}
