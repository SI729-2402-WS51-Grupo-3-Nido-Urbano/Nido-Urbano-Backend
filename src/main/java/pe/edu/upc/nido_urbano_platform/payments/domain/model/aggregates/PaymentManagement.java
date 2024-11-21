package pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.CreatePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.entities.Transactions;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentSchedule;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentStatus;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.PaymentType;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "payment_management")
public class PaymentManagement extends AuditableAbstractAggregateRoot<PaymentManagement> {
    @Embedded
    @NotNull(message = "El ContractId no puede ser nulo.")
    private ContractId contractId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El cronograma de pago no puede ser nulo.")
    private PaymentSchedule paymentSchedule;

    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "La fecha del próximo pago debe ser en el futuro.")
    private Date nextPayment;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del pago no puede ser nulo.")
    private PaymentStatus paymentStatus;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "El monto total no puede ser nulo.")
    @PositiveOrZero(message = "El monto debe ser positivo o zero")
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @NotNull(message = "El recordatorio enviado no puede ser nulo.")
    private Boolean reminderSent;

    @Column(nullable = true, precision = 10, scale = 2)
    @PositiveOrZero(message = "El primer pago debe ser igual o mayor a 0.")
    private BigDecimal firstPayment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "El tipo de pago no puede ser nulo.")
    private PaymentType paymentType;

    @OneToMany(mappedBy = "paymentManagement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payments> payments;

    public PaymentManagement() {
        this.contractId = new ContractId();
        this.paymentSchedule = PaymentSchedule.MONTHLY; // Valor predeterminado
        this.nextPayment = new Date();
        this.paymentStatus = PaymentStatus.PENDING; // Estado inicial
        this.totalAmount = BigDecimal.ZERO; // Monto predeterminado
        this.reminderSent = Boolean.FALSE; // Recordatorio no enviado
        this.firstPayment = BigDecimal.ZERO;
        this.paymentType = PaymentType.RENTAL;
        this.payments = new ArrayList<>();
    }

    public PaymentManagement(ContractId contractId, PaymentSchedule schedule, BigDecimal totalAmount, PaymentType type,
                             Date date) {
        this.contractId = contractId;
        this.paymentSchedule = schedule; // Valor predeterminado
        this.paymentStatus = PaymentStatus.PENDING; // Estado inicial
        this.totalAmount = totalAmount; // Monto predeterminado
        this.reminderSent = Boolean.FALSE; // Recordatorio no enviado
        this.paymentType = type;
        this.payments = new ArrayList<>();
        if (paymentType == PaymentType.SALE) {
            this.payments.add(new Payments(contractId, date, new BigDecimal("1000"), this));
        } else {
            this.payments.add(new Payments(contractId, date, totalAmount, this));
        }

        this.nextPayment = date;
        this.firstPayment = totalAmount;
    }

    public PaymentManagement(CreatePaymentManagementCommand command) {
        this.contractId = new ContractId(command.contractId());
        this.paymentSchedule = command.schedule(); // Valor predeterminado
        this.paymentStatus = PaymentStatus.PENDING; // Estado inicial
        this.totalAmount = command.totalAmount(); // Monto predeterminado
        this.reminderSent = Boolean.FALSE; // Recordatorio no enviado
        this.paymentType = command.type();
        this.payments = new ArrayList<>();

        // Crear y asociar pagos
        Payments payment = new Payments(contractId, command.nextPayment(), command.totalAmount(), this);

        if (this.paymentType == PaymentType.SALE) {
            payment.setPaymentAmount(new BigDecimal("1000"));
            payment.setRemainingAmount(new BigDecimal("1000"));
        } else {
            payment.setPaymentAmount(this.totalAmount);
            payment.setRemainingAmount(this.totalAmount);
        }
        this.firstPayment = payment.getPaymentAmount();
        payment.setPaymentManagement(this); // Asociar el PaymentManagement con el Payment

        this.addPayment(payment);
    }

    public void updateInformation(Long contractId, PaymentSchedule paymentSchedule, Date nextPayment,
                                  PaymentStatus paymentStatus, BigDecimal totalAmount, boolean reminderSent,
                                  PaymentType paymentType){
        this.contractId = new ContractId(contractId);
        this.paymentSchedule = paymentSchedule;
        this.nextPayment = nextPayment;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.reminderSent = reminderSent;
        this.paymentType = paymentType;
        if (paymentType == PaymentType.RENTAL) {
            firstPayment = totalAmount;
        }
        updateTotalAmount();
        calculateNextPaymentDate();
        updatePaymentStatus();
    }

    public void addPayment(Payments payment) {
        this.payments.add(payment);
        payment.setPaymentManagement(this);
        calculateNextPaymentDate();
        updatePaymentStatus();  // Actualizamos el estado cada vez que se añade un pago
        updateTotalAmount();
    }

    public void removePayment(Payments payment) {
        this.payments.remove(payment);
        payment.setPaymentManagement(null);
        updatePaymentStatus();  // Actualizamos el estado después de eliminar un pago
    }

    public void calculateNextPaymentDate() {
        // Verificamos si la lista de pagos está vacía, en cuyo caso usamos la fecha de inicio como referencia
        if (this.payments.isEmpty()) {
            this.nextPayment = new Date(); // Si no hay pagos, utilizamos la fecha actual
        }

        // Tomamos la fecha del último pago realizado
        Payments lastPayment = this.payments.getLast();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastPayment.getPaymentDate()); // Establecemos la fecha del último pago

        // Calculamos la siguiente fecha de acuerdo con el cronograma de pago
        switch (this.paymentSchedule) {
            case MONTHLY:
                calendar.add(Calendar.MONTH, 1);  // Añadimos un mes
                break;
            case QUARTERLY:
                calendar.add(Calendar.MONTH, 3);  // Añadimos tres meses
                break;
            case WEEKLY:
                calendar.add(Calendar.WEEK_OF_YEAR, 1);  // Añadimos una semana
                break;
            case ANNUALLY:
                calendar.add(Calendar.YEAR, 1);  // Añadimos un año
                break;
            default:
                throw new IllegalArgumentException("Cronograma no soportado: " + this.paymentSchedule);
        }

        this.nextPayment = calendar.getTime(); // Retornamos la nueva fecha de pago calculada
    }

    // Método para actualizar el estado de PaymentManagement
    public void updatePaymentStatus() {
        // Verificar si todos los pagos están completados
        boolean allPaymentsCompleted = payments.stream()
                .allMatch(payment -> payment.getPaymentStatus() == PaymentStatus.COMPLETED);

        // Si todos los pagos están completos, cambiamos el estado de PaymentManagement a COMPLETED
        if (allPaymentsCompleted) {
            this.paymentStatus = PaymentStatus.COMPLETED;
        } else if (payments.stream().anyMatch(payment -> payment.getPaymentStatus() == PaymentStatus.PENDING)) {
            // Si al menos un pago está en estado PENDING, cambiamos el estado de PaymentManagement a PENDING
            this.paymentStatus = PaymentStatus.PENDING;
        } else if (this.paymentStatus == PaymentStatus.PENDING && new Date().after(this.nextPayment)) {
            // Si el estado es PENDING y la fecha actual es después de la fecha de pago, lo cambiamos a LATE
            this.paymentStatus = PaymentStatus.LATE;
        }

    }

    public void updateTotalAmount() {
        if (paymentType == PaymentType.RENTAL) {
            BigDecimal totalPendingOrLate = payments.stream()
                    .filter(payment -> payment.getPaymentStatus() == PaymentStatus.PENDING || payment.getPaymentStatus() == PaymentStatus.LATE)
                    .map(Payments::getRemainingAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add); // Total de los pagos pendientes o atrasados
            totalAmount = totalPendingOrLate;
        }
    }

    public void addTransaction(int index, Transactions transaction) {
        this.payments.get(index).addTransaction(transaction);
        calculateNextPaymentDate();
        updatePaymentStatus();  // Actualizamos el estado cada vez que se añade un pago
        updateTotalAmount();
    }

    public BigDecimal getRemainingAmount() {
        BigDecimal remaining = null;
        if (paymentType == PaymentType.SALE) {
            // Si es SALE, el totalAmount se mantiene fijo (suponiendo que el primer pago es el monto total de la venta)
            remaining = payments.stream()
                    .map(Payments::getRemainingAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return remaining;
    }
}
