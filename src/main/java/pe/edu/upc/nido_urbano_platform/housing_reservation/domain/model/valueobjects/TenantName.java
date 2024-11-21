package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TenantName(String tenantName) {
    public TenantName() {
        this(null);
    }
    public TenantName {
        if (tenantName == null || tenantName.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
