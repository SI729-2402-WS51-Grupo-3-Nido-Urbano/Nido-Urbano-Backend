package pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects;

public record UserId(Long userId) {
    public UserId {
        if (userId <= 0) {
            throw new IllegalArgumentException("User userId cannot be negative or zero");
        }
    }

    public UserId() {
        this(0L);
    }
}
