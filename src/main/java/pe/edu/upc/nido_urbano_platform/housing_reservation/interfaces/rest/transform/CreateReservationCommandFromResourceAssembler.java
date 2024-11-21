package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.startDate(),
                resource.endDate(),
                resource.tenantAddress(),
                resource.tenantName(),
                resource.houseAddress(),
                resource.houseName(),
                resource.houseId());
    }
}
