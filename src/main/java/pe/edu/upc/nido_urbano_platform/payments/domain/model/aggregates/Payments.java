package pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentStatus;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payments extends AuditableAbstractAggregateRoot<Payments> {
    @Embedded
    @NotNull(message = "El ContractId no puede ser nulo.")
    private ContractId contractId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull(message = "La fecha de pago no puede ser nula.")
    @Future(message = "La fecha de pago debe ser en el futuro.")
    private Date paymentDate; // Fecha máxima para realizar el pago

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "El monto de pago no puede ser nulo.")
    private BigDecimal paymentAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "El monto de pago no puede ser nulo.")
    @PositiveOrZero(message = "El monto debe ser positivo o zero")
    private BigDecimal remainingAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "El estado de pago no puede ser nulo.")
    private PaymentStatus paymentStatus; // Estado inicial

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_management_id", nullable = false)
    private PaymentManagement paymentManagement;

    public Payments() {
        this.contractId = new ContractId(); // Contrato predeterminado.
        this.paymentDate = new Date(); // Fecha actual como predeterminada.
        this.paymentAmount = BigDecimal.ZERO; // Monto inicial predeterminado.
        this.paymentStatus = PaymentStatus.PENDING; // Estado inicial PENDING.
        this.transactions = new ArrayList<>(); // Lista vacía inicial para transacciones.
        this.remainingAmount = BigDecimal.ZERO;
        this.paymentManagement = new PaymentManagement();
    }

    public Payments(ContractId contractId, Date paymentDate, BigDecimal paymentAmount, PaymentManagement paymentManagement) {
        if (contractId == null) {
            throw new IllegalArgumentException("El contractId no puede ser nulo.");
        }
        if (paymentAmount != null && paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de pago debe ser mayor a 0.");
        }

        this.contractId = contractId; // Usar el contractId recibido.
        this.paymentDate = paymentDate != null ? paymentDate : new Date(); // Usar la fecha actual si es nula.
        this.paymentAmount = paymentAmount != null ? paymentAmount : BigDecimal.ZERO; // Usar 0 como monto predeterminado.
        this.paymentStatus = PaymentStatus.PENDING; // Siempre comienza como PENDING.
        this.transactions = new ArrayList<>();      // Lista vacía inicial para transacciones.
        this.remainingAmount = paymentAmount != null ? paymentAmount : BigDecimal.ZERO;
        this.paymentManagement = paymentManagement; // Asociar gestión de pagos.
    }

    public Payments(CreatePaymentCommand command, PaymentManagement paymentManagement){
        if (command.contractId() == null) {
            throw new IllegalArgumentException("El contractId no puede ser nulo.");
        }
        if (command.paymentAmount() != null && command.paymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de pago debe ser mayor a 0.");
        }
        if (paymentManagement == null) {
            throw new IllegalArgumentException("El PaymentManagement no puede ser nulo.");
        }
        this.contractId = new ContractId(command.contractId());
        this.paymentDate = command.paymentDay();
        this.paymentAmount = command.paymentAmount();
        this.paymentStatus = PaymentStatus.PENDING;
        this.transactions = new ArrayList<>();
        this.remainingAmount = command.paymentAmount();
        this.paymentManagement = paymentManagement;
    }

    public void updateInformation(Long contractId, Date paymentDate, BigDecimal paymentAmount,
                                  BigDecimal remainingAmount, PaymentStatus paymentStatus) {
        this.contractId = new ContractId(contractId);
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.remainingAmount = remainingAmount;
        this.paymentStatus = paymentStatus;
        updateRemainingAmount();
        updatePaymentStatus();
    }

    public void addTransaction(Transactions transaction) {
        if (this.paymentStatus == PaymentStatus.CANCELLED) {
            throw new IllegalStateException("No se pueden registrar transacciones en un pago cancelado.");
        }

        if (transaction.getTransactionAmount().compareTo(this.remainingAmount) > 0) {
            throw new IllegalArgumentException("La transacción excede el saldo pendiente.");
        }

        this.transactions.add(transaction);
        transaction.setPayment(this); // Relación bidireccional
        updateRemainingAmount();
    }

    public void removeTransaction(Transactions transaction) {
        this.transactions.remove(transaction);
        transaction.setPayment(null); // Eliminación de referencia.
    }

    public void updateRemainingAmount() {
        BigDecimal totalTransactionsAmount = BigDecimal.ZERO;

        for (Transactions transaction : this.transactions) {
            totalTransactionsAmount = totalTransactionsAmount.add(transaction.getTransactionAmount());
        }

        this.remainingAmount = this.paymentAmount.subtract(totalTransactionsAmount);
        updatePaymentStatus();
    }

    public void updatePaymentStatus() {
        if (this.remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            this.paymentStatus = PaymentStatus.COMPLETED;
        } else if (this.paymentStatus != PaymentStatus.CANCELLED && new Date().after(this.paymentDate)) {
            // Cambia al estado de LATE solo como registro, ya que no cambia nada de funcionlidad en el back.
            this.paymentStatus = PaymentStatus.LATE;
        }
    }

    public void cancelPayment() {
        if (this.paymentStatus == PaymentStatus.COMPLETED) {
            throw new IllegalStateException("No se puede cancelar un pago ya completado.");
        }

        this.paymentStatus = PaymentStatus.CANCELLED;
        // No borrar las transacciones, solo actualizar el estado del pago.
    }

    public void validatePaymentStatus() {
        if (this.paymentStatus == PaymentStatus.PENDING){
            throw new IllegalStateException("El pago está pendiente");
        }
        if (this.paymentStatus == PaymentStatus.LATE && new Date().after(this.paymentDate)) {
            throw new IllegalStateException("El pago está vencido y no se ha completado.");
        }

        if (this.paymentStatus == PaymentStatus.CANCELLED) {
            throw new IllegalStateException("El pago ha sido cancelado.");
        }
        // Si está completado, todo está bien, no hace falta validación adicional
        if (this.paymentStatus == PaymentStatus.COMPLETED) {
            throw new IllegalStateException("El pago está completado.");
        }
    }
}
