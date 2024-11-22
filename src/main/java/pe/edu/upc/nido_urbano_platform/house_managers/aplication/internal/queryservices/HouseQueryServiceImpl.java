package pe.edu.upc.nido_urbano_platform.house_managers.aplication.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetAllHousesQuery;
//import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByHouseModalQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetHouseByHouseModalQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetHouseByIdQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetHouseByUserPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.HouseModal;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.UserPropertyId;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.services.HouseQueryService;
import pe.edu.upc.nido_urbano_platform.house_managers.infrastructure.persistence.jpa.repositories.HouseRepository;

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
    public List<House> handle(GetHouseByHouseModalQuery query) {
        HouseModal houseModal = HouseModal.valueOf(query.houseModal().toUpperCase());
        return this.houseRepository.findByHouseModal(houseModal);
    }


    @Override
    public Optional<House> handle(GetHouseByIdQuery query) {
        return this.houseRepository.findById(query.houseId());
    }

    @Override
    public List<House> handle(GetHouseByUserPropertyIdQuery query) {
        return this.houseRepository.findByUserPropertyId(new UserPropertyId(query.userPropertyId()));
    }
}
