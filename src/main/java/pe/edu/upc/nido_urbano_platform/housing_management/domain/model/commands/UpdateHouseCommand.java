package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;

public record UpdateHouseCommand(HouseCode houseCode,
                                 String startDate,
                                 String endDate,
                                 String street,
                                 String tenantName) {
}
