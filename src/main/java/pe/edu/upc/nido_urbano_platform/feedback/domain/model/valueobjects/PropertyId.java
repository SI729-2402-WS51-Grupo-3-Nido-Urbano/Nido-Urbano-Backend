package pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects;

public record PropertyId(Long propertyId) {
    public PropertyId {
        if (propertyId <= 0) {
            throw new IllegalArgumentException("Property propertyId cannot be negative or zero");
        }
    }

    public PropertyId() {
        this(0L);
    }
}
