package pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects;

public record TenantId(Long tenantId) {
    public TenantId {
        if (tenantId <= 0) {
            throw new IllegalArgumentException("Property propertyId cannot be negative or zero");
        }
    }

    public TenantId() {
        this(0L);
    }
}
