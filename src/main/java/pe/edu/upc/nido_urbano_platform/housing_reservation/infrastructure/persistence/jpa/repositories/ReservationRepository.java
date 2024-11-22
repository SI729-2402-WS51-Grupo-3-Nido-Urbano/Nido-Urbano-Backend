package pe.edu.upc.nido_urbano_platform.housing_reservation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByStartDate(Date startDate);
    boolean existsByStartDateAndIdIsNot(Date startDate, Long id);
    Optional<Reservation> findByStartDate(Date startDate);
    Optional<Reservation> findByEndDate(Date endDate);
}
