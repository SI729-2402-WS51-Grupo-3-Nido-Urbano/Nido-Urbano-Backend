package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands;

public record CreateReservationCommand(String startDate, String endDate, String street) {
}
