package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects;

import java.util.UUID;

public record HouseCode(String houseCode) {
    public HouseCode() {
        this(UUID.randomUUID().toString());
    }
    public HouseCode {
        if (houseCode == null || houseCode.isBlank()) {
            throw new IllegalArgumentException("Student code cannot be null or blank");
        }
        if (houseCode.length() != 36) {
            throw new IllegalArgumentException("Student code must be 36 characters long");
        }
        if (!houseCode.matches("[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}")) {
            throw new IllegalArgumentException("Student code must be a valid UUID");
        }
    }
}
