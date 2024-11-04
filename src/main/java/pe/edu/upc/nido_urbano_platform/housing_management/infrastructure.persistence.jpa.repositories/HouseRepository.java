package pe.edu.upc.nido_urbano_platform.housing_management.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.ReservationId;

import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByHouseCode(HouseCode houseCode);
    Optional<House> findByReservationId(ReservationId reservationId);
    boolean existsByHouseCode(HouseCode houseCode);
}
