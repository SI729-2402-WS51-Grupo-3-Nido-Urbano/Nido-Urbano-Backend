package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources;

import javax.print.attribute.DateTimeSyntax;
import java.util.Date;

public record CreateReservationResource(Date startDate,
                                        Date endDate,
                                        String tenantAddress,
                                        String tenantName,
                                        String houseAddress,
                                        String houseName,
                                        Long houseId) {
}
