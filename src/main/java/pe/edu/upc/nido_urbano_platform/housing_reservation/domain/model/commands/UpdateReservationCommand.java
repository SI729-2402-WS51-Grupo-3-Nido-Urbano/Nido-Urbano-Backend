package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands;

import java.util.Date;

public record UpdateReservationCommand(Long reservationId, String startDate, String endDate, String street) {

}
