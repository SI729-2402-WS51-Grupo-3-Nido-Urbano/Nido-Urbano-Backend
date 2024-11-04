package pe.edu.upc.nido_urbano_platform.house.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record AddressHouse(String address) {
    public AddressHouse() {
        this(null);
    }

    public AddressHouse {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
