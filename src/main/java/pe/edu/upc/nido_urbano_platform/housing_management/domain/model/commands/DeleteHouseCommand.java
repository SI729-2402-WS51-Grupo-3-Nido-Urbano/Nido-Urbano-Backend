package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;

public record DeleteHouseCommand(HouseCode houseCode) {
}
