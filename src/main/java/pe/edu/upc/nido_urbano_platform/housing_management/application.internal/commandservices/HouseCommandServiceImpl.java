package pe.edu.upc.nido_urbano_platform.housing_management.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.housing_management.application.internal.outboundservices.acl.ExternalReservationService;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.UpdateHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.services.HouseCommandService;
import pe.edu.upc.nido_urbano_platform.housing_management.infrastructure.persistence.jpa.repositories.HouseRepository;

import java.util.Optional;

@Service
public class HouseCommandServiceImpl implements HouseCommandService {

    private final HouseRepository houseRepository;
    private final ExternalReservationService externalReservationService;

    public HouseCommandServiceImpl(HouseRepository houseRepository, ExternalReservationService externalReservationService) {
        this.houseRepository = houseRepository;
        this.externalReservationService = externalReservationService;
    }

    @Override
    public HouseCode handle(CreateHouseCommand command) {
        var reservationId = this.externalReservationService.fetchReservationIdByStartDate(command.startDate());
        if (reservationId.isPresent()) {
            this.houseRepository.findByReservationId(reservationId.get()).ifPresent(house -> {
                throw new IllegalArgumentException("House already exists");
            });
        }

        reservationId = this.externalReservationService.createReservation(command.startDate(), command.endDate(), command.street());
        if (reservationId.isEmpty()) {
            throw new IllegalArgumentException("Unable to create reservation");
        }

        var house = new House(reservationId.get());
        this.houseRepository.save(house);
        return house.getHouseCode();
    }

    @Override
    public Optional<House> handle(UpdateHouseCommand command) {
        var optionalHouse = this.houseRepository.findByHouseCode(command.houseCode());
        if (optionalHouse.isEmpty()) {
            throw new IllegalArgumentException("House not found");
        }

        if (this.externalReservationService.existsReservationByStartDateAndIdIsNot(command.startDate(), optionalHouse.get().getReservationId())) {
            throw new IllegalArgumentException("House with start date " + command.startDate() + " already exists");
        }

        var reservationId = this.externalReservationService.updateReservation(optionalHouse.get().getReservationId(), command.startDate(), command.endDate(), command.street());

        if (reservationId.isEmpty()) {
            throw new IllegalArgumentException("Unable to update reservation");
        }

        // Update house
        // this.houseRepository.save(optionalHouse.get());
        return optionalHouse;
    }

    @Override
    public void handle(DeleteHouseCommand command) {
        if (!this.houseRepository.existsByHouseCode(command.houseCode())) {
            throw new IllegalArgumentException("House not found");
        }
        var optionalHouse = this.houseRepository.findByHouseCode(command.houseCode());
        this.externalReservationService.deleteReservation(optionalHouse.get().getReservationId());
        this.houseRepository.deleteById(optionalHouse.get().getId());
    }
}
