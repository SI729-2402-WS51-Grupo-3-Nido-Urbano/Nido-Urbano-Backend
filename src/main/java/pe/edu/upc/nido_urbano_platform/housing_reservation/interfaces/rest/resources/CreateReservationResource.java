package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources;

import java.sql.Date;

public record CreateReservationResource(Date startDate,
                                        Date endDate,
                                        String street,
                                        String tenantName) {
}
