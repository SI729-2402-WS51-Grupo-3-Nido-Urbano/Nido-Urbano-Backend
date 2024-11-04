package pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.house.domain.model.aggregates.House;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
    boolean existsByAddressAndHouseTypeAndHouseModal(String address, String houseType, String houseModal);
    boolean existsByAddressAndHouseTypeAndHouseModalAndIdIsNot(String address, String houseType, String houseModal, Long id);
    Optional<House> findByUserPropertyId(Long userPropertyId);
    List<House> findByHouseModal(String houseModal);
    List<House> findByAddress(String address);
}
