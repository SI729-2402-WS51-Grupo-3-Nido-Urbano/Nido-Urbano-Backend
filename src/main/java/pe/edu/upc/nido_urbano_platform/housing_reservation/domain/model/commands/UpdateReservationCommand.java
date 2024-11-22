package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands;

import javax.print.attribute.DateTimeSyntax;
import java.util.Date;

public record UpdateReservationCommand(Long reservationId,
                                       Date startDate,
                                       Date endDate,
                                       String tenantAddress,
                                       String tenantName,
                                       String houseAddress,
                                       String houseName,
                                       Long houseId) {

}
