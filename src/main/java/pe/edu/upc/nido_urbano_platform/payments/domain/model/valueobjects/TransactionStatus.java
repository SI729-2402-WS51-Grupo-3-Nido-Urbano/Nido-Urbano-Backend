package pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects;

public enum TransactionStatus {
    PENDING,      // En proceso
    SUCCESS,      // Transacción completada con éxito
    ERROR,        // Error genérico
    CANCELLED,    // Transacción anulada
    IN_REVIEW,    // En revisión
    FAILED        // Fallo específico
}
