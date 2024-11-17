package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects.*;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name="reservations")
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    /**
     * Start Date
     */
    @Getter
    @NotNull
    @NotBlank
    @Column(name="start_date", length = 40, nullable=false)
    private String startDate;

    /**
     * End Date
     */
    @Getter
    @NotBlank
    @Column(name="end_date", length = 50, nullable=false)
    private String endDate;

    /**
     * Tenant Address
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "street", column = @Column(name = "tenant_street_address", length = 100, nullable = false))
    })
    private TenantAddress address;

    /**
     * Tenant Name
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "tenantName", column = @Column(name = "tenant_name", length = 100, nullable = false))
    })
    private TenantName tenantName;

    /**
     * User ID
     *//*
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))
    })
    private UserId userId;*/

    /**
     * House Address
     *//*
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseAddress", column = @Column(name = "house_address", length = 100, nullable = false))
    })
    private HouseAddress houseAddress;*/

    /**
     * House Name
     *//*
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseName", column = @Column(name = "house_name", length = 100, nullable = false))
    })
    private HouseName houseName;*/

    /**
     * House ID
     *//*
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseId", column = @Column(name = "house_id", nullable = false))
    })
    private HouseId houseId;*/


    //---------------------------------------------------
    public Reservation(String startDate,
                       String endDate,
                       String street,
                       String tenantName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = new TenantAddress(street);
        this.tenantName = new TenantName(tenantName);
    }

    public Reservation() {
    }

    public void updateTenantAddress(String street) {
        this.address = new TenantAddress(street);
    }

    public String getAddress() {
        return address.street();
    }

    public String getTenantName() {
        return tenantName.tenantName();
    }

    public Reservation(CreateReservationCommand command) {
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.address = new TenantAddress(command.street());
        this.tenantName = new TenantName(command.tenantName());
    }

    public Reservation updateInformation(String startDate,
                                         String endDate,
                                         String street,
                                         String tenantName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = new TenantAddress(street);
        this.tenantName = new TenantName(tenantName);
        return this;
    }
}
