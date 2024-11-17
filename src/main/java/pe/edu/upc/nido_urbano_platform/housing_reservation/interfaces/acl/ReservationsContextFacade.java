package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByStartDateQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services.ReservationCommandService;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services.ReservationQueryService;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources.ReservationResource;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;

import java.util.Optional;

@Service
public class ReservationsContextFacade {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    public ReservationsContextFacade(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    public Optional<ReservationResource> fetchReservationById(Long reservationId) {
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var optionalReservation = reservationQueryService.handle(getReservationByIdQuery);
        if (optionalReservation.isEmpty()) {
            return Optional.empty();
        }
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(optionalReservation.get());
        return Optional.of(reservationResource);
    }

    public Long fetchReservationIdByStartDate(String startDate) {
        var getReservationByStartDateQuery = new GetReservationByStartDateQuery(startDate);
        var optionalReservation = reservationQueryService.handle(getReservationByStartDateQuery);
        if (optionalReservation.isEmpty()) {
            return 0L;
        }
        return optionalReservation.get().getId();
    }

    public boolean existsReservationByStartDateAndIdIsNot(String startDate, Long id) {
        var getReservationByStartDateQuery = new GetReservationByStartDateQuery(startDate);
        var optionalReservation = reservationQueryService.handle(getReservationByStartDateQuery);
        if (optionalReservation.isEmpty()) {
            return false;
        }
        return optionalReservation.get().getId() != id;
    }

    public Long createReservation(String startDate, String endDate, String street, String tenantName) {
        var createReservationCommand = new CreateReservationCommand(startDate, endDate, street, tenantName);
        var reservationId = reservationCommandService.handle(createReservationCommand);
        if (reservationId.equals(null)) {
            return 0L;
        }
        return reservationId;
    }

    public Long updateReservation(Long reservationId, String startDate, String endDate, String street, String tenantName) {
        var updateReservationCommand = new UpdateReservationCommand(reservationId, startDate, endDate, street, tenantName);
        var optionalReservation = reservationCommandService.handle(updateReservationCommand);
        if (optionalReservation.isEmpty()) {
            return 0L;
        }
        return optionalReservation.get().getId();
    }

    public void deleteReservation(Long reservationId) {
        var deleteReservationCommand = new DeleteReservationCommand(reservationId);
        reservationCommandService.handle(deleteReservationCommand);
    }
}
