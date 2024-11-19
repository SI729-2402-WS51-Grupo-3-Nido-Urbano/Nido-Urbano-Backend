package pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects;

public record LandlordId(Long landLordId) {
    public LandlordId {
        if (landLordId <= 0) {
            throw new IllegalArgumentException("Property landLordId cannot be negative or zero");
        }
    }

    public LandlordId() {
        this(0L);
    }
}
