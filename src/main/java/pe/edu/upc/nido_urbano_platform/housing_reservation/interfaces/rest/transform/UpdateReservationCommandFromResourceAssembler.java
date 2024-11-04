package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources.ReservationResource;

public record UpdateReservationCommandFromResourceAssembler() {
    public static UpdateReservationCommand toCommandFromResource(Long reservationId, ReservationResource resource) {
        return new UpdateReservationCommand(reservationId, resource.startDate(), resource.endDate(), resource.street());
    }
}
