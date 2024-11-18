package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands;

import java.sql.Date;

public record CreateHouseCommand(Date startDate,
                                 Date endDate,
                                 String street,
                                 String tenantName) {
}
