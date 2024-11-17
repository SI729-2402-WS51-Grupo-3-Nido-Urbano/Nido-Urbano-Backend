package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects;

public record HouseId(Long houseId) {
    public HouseId {
        if (houseId <= 0) {
            throw new IllegalArgumentException("HouseId cannot be negative or zero");
        }
    }

    public HouseId() {
        this(0L);
    }
}
