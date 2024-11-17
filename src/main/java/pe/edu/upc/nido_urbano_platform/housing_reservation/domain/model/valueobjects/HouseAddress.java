package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record HouseAddress(String address) {
    public HouseAddress() {
        this(null);
    }
    public HouseAddress {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
