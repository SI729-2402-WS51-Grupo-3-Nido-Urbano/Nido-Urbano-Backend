package pe.edu.upc.nido_urbano_platform.house.domain.services;

import pe.edu.upc.nido_urbano_platform.house.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetAllHousesQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByHouseModalQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByIdQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetHouseByUserPropertyIdQuery;

import java.util.List;
import java.util.Optional;

public interface HouseQueryService {
    List<House> handle(GetAllHousesQuery query);
    List<House> handle(GetHouseByHouseModalQuery query);
    Optional<House> handle(GetHouseByIdQuery query);
    Optional<House> handle(GetHouseByUserPropertyIdQuery query);
}
