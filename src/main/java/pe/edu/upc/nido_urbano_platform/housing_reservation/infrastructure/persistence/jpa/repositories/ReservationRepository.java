package pe.edu.upc.nido_urbano_platform.housing_reservation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByStartDate(String startDate);
    boolean existsByStartDateAndIdIsNot(String startDate, Long id);
    Optional<Reservation> findByStartDate(String startDate);
    Optional<Reservation> findByEndDate(String endDate);
}
