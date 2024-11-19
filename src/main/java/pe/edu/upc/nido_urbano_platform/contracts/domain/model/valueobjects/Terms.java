package pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects;

public record Terms(String terms) {

    public Terms() {
        this(String.valueOf(0L));
    }
}
