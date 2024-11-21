package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services.ReservationCommandService;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.services.ReservationQueryService;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources.CreateReservationResource;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources.ReservationResource;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform.UpdateReservationCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Reservation Management Endpoints")
public class ReservationsController {

    private final ReservationQueryService reservationQueryService;
    private final ReservationCommandService reservationCommandService;

    public ReservationsController(ReservationQueryService reservationQueryService, ReservationCommandService reservationCommandService) {
        this.reservationQueryService = reservationQueryService;
        this.reservationCommandService = reservationCommandService;
    }

    @PostMapping
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) {

        var createReservationCommand = CreateReservationCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var reservationId = this.reservationCommandService.handle(createReservationCommand);

        if (reservationId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var optionalReservation = this.reservationQueryService.handle(getReservationByIdQuery);

        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(optionalReservation.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResource>> getAllReservations() {
        var getAllReservationsQuery = new GetAllReservationsQuery();
        var reservations = this.reservationQueryService.handle(getAllReservationsQuery);
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable Long reservationId) {
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var optionalReservation = this.reservationQueryService.handle(getReservationByIdQuery);
        if (optionalReservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(optionalReservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationResource> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationResource resource) {
        var updateReservationCommand = UpdateReservationCommandFromResourceAssembler.toCommandFromResource(reservationId, resource);
        var optionalReservation = this.reservationCommandService.handle(updateReservationCommand);

        if (optionalReservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(optionalReservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
        var deleteReservationCommand = new DeleteReservationCommand(reservationId);
        this.reservationCommandService.handle(deleteReservationCommand);
        return ResponseEntity.noContent().build();
    }
}
