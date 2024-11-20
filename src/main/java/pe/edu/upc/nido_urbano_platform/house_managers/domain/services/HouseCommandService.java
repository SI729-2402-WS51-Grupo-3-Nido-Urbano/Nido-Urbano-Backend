package pe.edu.upc.nido_urbano_platform.house_managers.domain.services;

import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.UpdateHouseCommand;

import java.util.Optional;

public interface HouseCommandService {
    Long handle(CreateHouseCommand command);
    Optional<House> handle(UpdateHouseCommand command);
    void handle(DeleteHouseCommand command);
}
