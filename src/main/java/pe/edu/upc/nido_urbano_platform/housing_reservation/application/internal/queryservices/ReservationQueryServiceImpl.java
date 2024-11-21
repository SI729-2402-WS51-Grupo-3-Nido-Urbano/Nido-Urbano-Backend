package pe.edu.upc.nido_urbano_platform.housing_reservation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByEndDateQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByStartDateQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services.ReservationQueryService;
import pe.edu.upc.nido_urbano_platform.housing_reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {

    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> handle(GetAllReservationsQuery query) {
        return this.reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return this.reservationRepository.findById(query.reservationId());
    }

    @Override
    public Optional<Reservation> handle(GetReservationByStartDateQuery query) {
        return this.reservationRepository.findByStartDate(query.startDate());
    }

    @Override
    public Optional<Reservation> handle(GetReservationByEndDateQuery query) {
        return this.reservationRepository.findByEndDate(query.endDate());
    }
}