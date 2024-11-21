package pe.edu.upc.nido_urbano_platform.payments.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreateTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.TransactionStatus;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.entities.AuditableModel;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transactions extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "La fecha de la transacción no puede estar en el futuro.")
    private Date transactionDate;

    @Column(nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0.")
    @Digits(integer = 8, fraction = 2, message = "El monto no debe exceder los 8 dígitos enteros y 2 decimales.")
    private BigDecimal transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "El estado de la transaccion no puede ser nulo.")
    private TransactionStatus transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payments payment;

    public Transactions() {
        this.transactionDate = new Date(); // Fecha actual predeterminada.
        this.transactionStatus = TransactionStatus.SUCCESS;
    }

    public Transactions(BigDecimal transactionAmount) {
        this.transactionDate = new Date(); // Fecha actual predeterminada.
        this.transactionAmount = transactionAmount;
        this.transactionStatus = TransactionStatus.SUCCESS;
    }

    public Transactions(CreateTransactionCommand command, Payments payment) {
        this.transactionDate = new Date();
        this.transactionStatus = TransactionStatus.SUCCESS;
        this.transactionAmount = command.transactionAmount();
        this.payment = payment;
    }

    public void updateInformation(Date transactionDate, BigDecimal transactionAmount,TransactionStatus transactionStatus) {
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.transactionAmount = transactionAmount;
    }

    public Transactions(BigDecimal transactionAmount, TransactionStatus transactionStatus) {
        if (transactionAmount == null || transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero.");
        }
        if (transactionStatus == null) {
            throw new IllegalArgumentException("El estado de la transacción no puede ser nulo.");
        }
        this.transactionDate = new Date();
        this.transactionAmount = transactionAmount;
        this.transactionStatus = transactionStatus;
        this.payment = null;
    }

    public void linkPayment(Payments payment) {
        // Verificar si ya existe un pago asociado
        if (this.payment != null) {
            // Si hay un pago previo, eliminar la relación actual para evitar inconsistencias
            this.payment.getTransactions().remove(this);
        }

        // Establecer el nuevo pago
        this.payment = payment;

        // Agregar esta transacción al nuevo pago, asegurando la relación bidireccional
        if (payment != null && !payment.getTransactions().contains(this)) {
            payment.addTransaction(this);
        }
    }

    public void updateTransactionStatus(TransactionStatus newStatus) {
        if (this.transactionStatus == TransactionStatus.SUCCESS) {
            throw new IllegalStateException("No se puede cambiar el estado de una transacción exitosa.");
        }
        if (this.transactionStatus == TransactionStatus.ERROR && newStatus != TransactionStatus.SUCCESS) {
            throw new IllegalStateException("Una transacción fallida solo puede cambiar a EXITOSA después de una corrección.");
        }
        this.transactionStatus = newStatus;
    }
}
