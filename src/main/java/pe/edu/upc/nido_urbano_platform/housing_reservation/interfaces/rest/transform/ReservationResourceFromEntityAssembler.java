package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources.ReservationResource;

public record ReservationResourceFromEntityAssembler() {
    public static ReservationResource toResourceFromEntity(Reservation entity) {
        return new ReservationResource(entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTenantAddress().tenantAddress(),
                entity.getTenantName().tenantName(),
                entity.getHouseAddress().houseAddress(),
                entity.getHouseName().houseName(),
                entity.getHouseId().houseId());
    }
}
