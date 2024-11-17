package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;

import java.sql.Date;

public record UpdateHouseCommand(HouseCode houseCode,
                                 Date startDate,
                                 Date endDate,
                                 String street,
                                 String tenantName) {
}
