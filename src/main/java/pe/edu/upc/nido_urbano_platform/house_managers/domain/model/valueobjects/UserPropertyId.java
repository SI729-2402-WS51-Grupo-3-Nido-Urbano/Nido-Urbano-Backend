package pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserPropertyId(Long userPropertyId) {
    public UserPropertyId {
        if (userPropertyId < 0) {
            throw new IllegalArgumentException("UserProperty userPropertyId cannot be negative");
        }
    }

    public UserPropertyId() {
        this(0L);
    }
    public Long getValue() {
        return userPropertyId;
    }
}
