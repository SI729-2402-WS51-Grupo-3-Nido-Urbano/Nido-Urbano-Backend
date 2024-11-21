package pe.edu.upc.nido_urbano_platform.house_managers.aplication.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.UpdateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.AddressHouse;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.HouseModal;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.HouseType;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.services.HouseCommandService;
import pe.edu.upc.nido_urbano_platform.house_managers.infrastructure.persistence.jpa.repositories.HouseRepository;

import java.util.Optional;

@Service
public class HouseCommandServiceImpl implements HouseCommandService {

    private final HouseRepository houseRepository;

    public HouseCommandServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public Long handle(CreateHouseCommand command) {
        HouseType houseType = HouseType.valueOf(command.houseType().toUpperCase());
        HouseModal houseModal = HouseModal.valueOf(command.houseModal().toUpperCase());

        if (this.houseRepository.existsByAddressAndHouseTypeAndHouseModal(new AddressHouse(command.address()), houseType, houseModal)) {
            if (command.houseType().equals("HOUSE") && command.houseModal().equals("SALE")) {
                throw new IllegalArgumentException("House with houseAddress " + command.address() + " and house type " + command.houseType() + " and house modal " + command.houseModal() + " already exists");
            }
        }

        var house = new House(command);
        try {
            this.houseRepository.save(house);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving house: " + e.getMessage());
        }
        return house.getId();
    }

    @Override
    public Optional<House> handle(UpdateHouseCommand command) {

        HouseType houseType = HouseType.valueOf(command.houseType().toUpperCase());
        HouseModal houseModal = HouseModal.valueOf(command.houseModal().toUpperCase());

        var houseId = command.houseId();

        if (!this.houseRepository.existsById(houseId)) {
            throw new IllegalArgumentException("House with id " + houseId + " does not exist");
        }
        if (this.houseRepository.existsByAddressAndHouseTypeAndHouseModalAndIdIsNot(new AddressHouse(command.address()), houseType, houseModal, houseId)) {
            if (command.houseType().equals("HOUSE") && command.houseModal().equals("SALE")) {
                throw new IllegalArgumentException("House with houseAddress " + command.address() + " and house type " + command.houseType() + " and house modal " + command.houseModal() + " already exists");
            }
        }

        var houseToUpdate = this.houseRepository.findById(houseId).get();
        houseToUpdate.updateInformation(command.userPropertyId(),
                command.houseName(),
                command.address(),
                command.houseType(),
                command.houseModal(),
                command.price(),
                command.size(),
                command.description(),
                command.publicationDate(),
                command.startsCalification(),
                command.statusLandlord(),
                command.photo(),
                command.video(),
                command.termsConditions());
        try {
            var updatedHouse = this.houseRepository.save(houseToUpdate);
            return Optional.of(updatedHouse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating house: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteHouseCommand command) {
        if (!this.houseRepository.existsById(command.houseId())) {
            throw new IllegalArgumentException("House with id " + command.houseId() + " does not exist");
        }

        try {
            this.houseRepository.deleteById(command.houseId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting house: " + e.getMessage());
        }
    }
}