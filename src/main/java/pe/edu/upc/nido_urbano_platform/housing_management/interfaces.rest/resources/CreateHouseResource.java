package pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources;

import java.sql.Date;

public record CreateHouseResource(Date startDate,
                                  Date endDate,
                                  String address,
                                  String tenantName) {
}
