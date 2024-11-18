package pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Term {

    @Getter
    private final String description;

    @Getter
    private final Boolean agreed;

    public Term(String description, Boolean agreed) {
        this.description = description;
        this.agreed = agreed;
    }

    // Constructor sin argumentos para JPA
    public Term() {
        this.description = null;
        this.agreed = null;
    }
}

