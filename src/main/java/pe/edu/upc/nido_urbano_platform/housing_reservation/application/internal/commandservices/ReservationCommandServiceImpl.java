package pe.edu.upc.nido_urbano_platform.housing_reservation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services.ReservationCommandService;
import pe.edu.upc.nido_urbano_platform.housing_reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long handle(CreateReservationCommand command) {
        var startDate = command.startDate();
        if (this.reservationRepository.existsByStartDate(startDate)) {
            throw new IllegalArgumentException("Reservation with start date " + startDate + " already exists");
        }
        var reservation = new Reservation(command);
        try {
            this.reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving reservation: " + e.getMessage());
        }
        return reservation.getId();
    }

    @Override
    public Optional<Reservation> handle(UpdateReservationCommand command) {
        var reservationId = command.reservationId();
        var startDate = command.startDate();
        if (this.reservationRepository.existsByStartDateAndIdIsNot(startDate, reservationId)) {
            throw new IllegalArgumentException("Reservation with start date " + startDate + " already exists");
        }

        // If the reservation does not exist, throw an exception
        if (!this.reservationRepository.existsById(reservationId)) {
            throw new IllegalArgumentException("Reservation with id " + reservationId + " does not exist");
        }

        var reservationToUpdate = this.reservationRepository.findById(reservationId).get();
        reservationToUpdate.updateInformation(
                command.startDate().toLocalDate(), // Convert java.sql.Date to LocalDate
                command.endDate().toLocalDate(),   // Convert java.sql.Date to LocalDate
                command.street(),
                command.tenantName()
        );

        try {
            var updatedReservation = this.reservationRepository.save(reservationToUpdate);
            return Optional.of(updatedReservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating reservation: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteReservationCommand command) {
        // If the reservation does not exist, throw an exception
        if (!this.reservationRepository.existsById(command.reservationId())) {
            throw new IllegalArgumentException("Reservation with id " + command.reservationId() + " does not exist");
        }

        // Try to delete the reservation, if an error occurs, throw an exception
        try {
            this.reservationRepository.deleteById(command.reservationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting reservation: " + e.getMessage());
        }
    }
}
