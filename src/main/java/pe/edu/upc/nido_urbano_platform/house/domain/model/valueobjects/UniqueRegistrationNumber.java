package pe.edu.upc.nido_urbano_platform.house.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UniqueRegistrationNumber(String uniqueRegistrationNumber) {

    public UniqueRegistrationNumber() {
        this(null);
    }
    public UniqueRegistrationNumber {
        if (uniqueRegistrationNumber == null || uniqueRegistrationNumber.isBlank()) {
            throw new IllegalArgumentException("Unique registration number cannot be null or blank");
        }
        if (uniqueRegistrationNumber.length() != 12){
            throw new IllegalArgumentException("Unique registration number must be 12 characters long");
        }
    }
}
