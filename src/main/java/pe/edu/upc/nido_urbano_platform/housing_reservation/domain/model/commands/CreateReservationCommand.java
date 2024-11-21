package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands;

import java.sql.Date;

public record CreateReservationCommand(Date startDate,
                                       Date endDate,
                                       String street,
                                       String tenantName) {
}
