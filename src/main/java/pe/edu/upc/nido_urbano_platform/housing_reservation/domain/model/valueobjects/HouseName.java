package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record HouseName(String houseName) {
    public HouseName() {
        this(null);
    }
    public HouseName {
        if (houseName == null || houseName.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
