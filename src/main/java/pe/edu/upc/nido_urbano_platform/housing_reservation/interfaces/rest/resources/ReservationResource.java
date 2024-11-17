package pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.rest.resources;

public record ReservationResource(Long id,
                                  String startDate,
                                  String endDate,
                                  String street,
                                  String tenantName) {
}
