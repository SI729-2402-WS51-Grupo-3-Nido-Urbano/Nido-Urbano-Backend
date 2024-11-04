package pe.edu.upc.nido_urbano_platform.house.aplication.internal.commandservices;

import pe.edu.upc.nido_urbano_platform.house.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.UpdateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.services.HouseCommandService;
import pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories.HouseRepository;

import java.util.Optional;

public class HouseCommandServiceImpl implements HouseCommandService {

    private final HouseRepository houseRepository;

    public HouseCommandServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public Long handle(CreateHouseCommand command) {
        var address = command.address();
        var houseType = command.houseType();//HOUSE
        var houseModal = command.houseModal();//SALE
        if("HOUSE".equals(houseType) && "SALE".equals(houseModal)){
            if (this.houseRepository.existsByAddressAndHouseTypeAndHouseModal(address, houseType, houseModal)) {
                throw new IllegalArgumentException("House with address " + address + " already exists");
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
        var houseId = command.houseId();
        var address = command.address();
        var houseType = command.houseType();
        var houseModal = command.houseModal();

        if("HOUSE".equals(houseType) && "SALE".equals(houseModal)){
            if (this.houseRepository.existsByAddressAndHouseTypeAndHouseModalAndIdIsNot(address, houseType, houseModal, houseId)) {
                throw new IllegalArgumentException("House with address " + address + " already exists");
            }
        }
        if(!this.houseRepository.existsById(houseId)){
            throw new IllegalArgumentException("House with id " + houseId + " does not exist");
        }

        var houseToUpdate = this.houseRepository.findById(houseId).get();
        houseToUpdate.updateInformation(command.userPropertyId(),command.houseName(),command.address(),command.houseType(),command.houseModal(),command.price(),command.size(),command.description(),command.publicationDate(),command.startsCalification(),command.statusLandlord(),command.photo(),command.video());
        try{
            var updatedHouse = this.houseRepository.save(houseToUpdate);
            return Optional.of(updatedHouse);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating house: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteHouseCommand command) {
        if(!this.houseRepository.existsById(command.houseId())){
            throw new IllegalArgumentException("House with id " + command.houseId() + " does not exist");
        }

        try{
            this.houseRepository.deleteById(command.houseId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting house: " + e.getMessage());
        }

    }
}
