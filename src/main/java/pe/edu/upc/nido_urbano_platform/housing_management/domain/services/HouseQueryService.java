package pe.edu.upc.nido_urbano_platform.housing_management.domain.services;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetAllHousesQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByHouseCodeQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByReservationIdQuery;

import java.util.List;
import java.util.Optional;

public interface HouseQueryService {
    List<House> handle(GetAllHousesQuery query);
    Optional<House> handle(GetHouseByIdQuery query);
    Optional<House> handle(GetHouseByHouseCodeQuery query);
    Optional<House> handle(GetHouseByReservationIdQuery query);
}
