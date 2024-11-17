package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources;

import java.sql.Date;

public record ReservationResource(Long id,
                                  Date startDate,
                                  Date endDate,
                                  String street,
                                  String tenantName) {
}
