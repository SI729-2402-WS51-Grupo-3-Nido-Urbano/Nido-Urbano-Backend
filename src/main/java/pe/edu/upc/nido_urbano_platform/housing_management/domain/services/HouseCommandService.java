package pe.edu.upc.nido_urbano_platform.housing_management.domain.services;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.UpdateHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;

import java.util.Optional;

public interface HouseCommandService {
    HouseCode handle(CreateHouseCommand command);
    Optional<House> handle(UpdateHouseCommand command);
    void handle(DeleteHouseCommand command);
}
