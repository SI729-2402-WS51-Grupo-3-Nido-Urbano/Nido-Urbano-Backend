package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands;

import java.sql.Date;

public record UpdateReservationCommand(Long reservationId,
                                       String startDate,
                                       String endDate,
                                       String tenantAddress,
                                       String tenantName,
                                       String houseAddress,
                                       String houseName,
                                       Long houseId) {

}
