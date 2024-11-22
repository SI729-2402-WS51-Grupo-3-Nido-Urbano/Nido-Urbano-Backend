package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
/* TenantAddress will be the tenant houseAddress who is in search of a house */
public record TenantAddress(String tenantAddress) {
    public TenantAddress() {
        this(null);
    }
    public TenantAddress {
        if (tenantAddress == null || tenantAddress.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }

}
