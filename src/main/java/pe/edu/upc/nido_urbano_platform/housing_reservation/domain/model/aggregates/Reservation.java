package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects.TenantAddress;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name="reservations")
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name="start_date", length = 40, nullable=false)
    // Start Date
    private String startDate;

    @Getter
    @NotBlank
    @Column(name="end_date", length = 50, nullable=false)
    // End Date
    private String endDate;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "street", column = @Column(name = "tenant_street_address", length = 100, nullable = false))
    })
    private TenantAddress address;

    //---------------------------------------------------
    public Reservation(String startDate, String endDate, String street) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = new TenantAddress(street);
    }

    public Reservation() {
    }

    public void updateTenantAddress(String street) {
        this.address = new TenantAddress(street);
    }

    public String getAddress() {
        return address.street();
    }

    public Reservation(CreateReservationCommand command) {
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.address = new TenantAddress(command.street());
    }

    public Reservation updateInformation(String startDate, String endDate, String street) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = new TenantAddress(street);
        return this;
    }
}
