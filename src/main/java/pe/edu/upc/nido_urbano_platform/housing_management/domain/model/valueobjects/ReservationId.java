package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects;

public record ReservationId(Long reservationId) {
    public ReservationId {
        if (reservationId < 0) {
            throw new IllegalArgumentException("Profile profileId cannot be negative");
        }
    }

    public ReservationId() {
        this(0L);
    }
}
