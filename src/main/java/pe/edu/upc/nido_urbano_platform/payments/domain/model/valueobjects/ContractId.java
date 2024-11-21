package pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects;

public record ContractId(Long contractId) {
    public ContractId {
        if (contractId < 0) {
            throw new IllegalArgumentException("Contract contractId cannot be negative");
        }
    }

    public ContractId() {
        this(0L);
    }
}
