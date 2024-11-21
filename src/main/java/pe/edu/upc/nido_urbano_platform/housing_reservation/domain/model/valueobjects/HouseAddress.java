package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record HouseAddress(String houseAddress) {
    public HouseAddress() {
        this(null);
    }
    public HouseAddress {
        if (houseAddress == null || houseAddress.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
