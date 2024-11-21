package pe.edu.upc.nido_urbano_platform.house_managers.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.AddressHouse;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.HouseModal;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.HouseType;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.UserPropertyId;


import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
   boolean existsByAddressAndHouseTypeAndHouseModal(AddressHouse address, HouseType houseType, HouseModal houseModal);
    boolean existsByAddressAndHouseTypeAndHouseModalAndIdIsNot(AddressHouse address, HouseType houseType, HouseModal houseModal, Long id);
    Optional<House> findByUserPropertyId(UserPropertyId userPropertyId);
    List<House> findByHouseModal(HouseModal houseModal);
    List<House> findByAddress(AddressHouse address);
    boolean existsByAddress(AddressHouse address);
    boolean existsByAddressAndIdIsNot(AddressHouse address, Long houseId);
}
