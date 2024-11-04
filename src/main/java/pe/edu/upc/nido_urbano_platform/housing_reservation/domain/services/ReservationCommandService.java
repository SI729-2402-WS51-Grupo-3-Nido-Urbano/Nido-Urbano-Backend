package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services;

import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Long handle(CreateReservationCommand command);
    Optional<Reservation> handle(UpdateReservationCommand command);
    void handle(DeleteReservationCommand command);
}
