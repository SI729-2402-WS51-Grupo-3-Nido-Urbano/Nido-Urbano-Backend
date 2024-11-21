package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources;

import java.sql.Date;

public record ReservationResource(Long id,
                                  String startDate,
                                  String endDate,
                                  String tenantAddress,
                                  String tenantName,
                                  String houseAddress,
                                  String houseName,
                                  Long houseId) {
}
