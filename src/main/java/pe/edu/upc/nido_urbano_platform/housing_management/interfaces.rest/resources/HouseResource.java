package pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources;

import java.sql.Date;

public record HouseResource(String id,
                            Date startDate,
                            Date endDate,
                            String address,
                            String tenantName) {
}
