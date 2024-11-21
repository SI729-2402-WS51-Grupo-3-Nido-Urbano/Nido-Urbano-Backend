package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
/* TenantAddress will be the tenant address who is in search of a house */
public record TenantAddress(String street) {
    public TenantAddress() {
        this(null);
    }
    public TenantAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
