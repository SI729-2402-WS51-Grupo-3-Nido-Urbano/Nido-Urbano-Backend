package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services;

import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByEndDateQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByStartDateQuery;

import java.util.List;
import java.util.Optional;


public interface ReservationQueryService {
    List<Reservation> handle(GetAllReservationsQuery query);
    Optional<Reservation> handle(GetReservationByIdQuery query);
    Optional<Reservation> handle(GetReservationByStartDateQuery query);
    Optional<Reservation> handle(GetReservationByEndDateQuery query);
}
