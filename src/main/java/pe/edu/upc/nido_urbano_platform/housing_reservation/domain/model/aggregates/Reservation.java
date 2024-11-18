package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects.*;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="reservations")
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    /**
     * Start Date
     */
    @Getter
    @NotNull
    @Column(name = "start_date", length = 40, nullable = false)
    private Date startDate;

    /**
     * End Date
     */
    @Getter
    @NotNull
    @Column(name = "end_date", length = 50, nullable = false)
    private Date endDate;

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

    // Constructors
    public Reservation(LocalDate startDate, LocalDate endDate, String street, String tenantName) {
        validateDateDifference(startDate, endDate);
        this.startDate = Date.valueOf(startDate); // Convert LocalDate to java.sql.Date
        this.endDate = Date.valueOf(endDate);     // Convert LocalDate to java.sql.Date
        this.address = new TenantAddress(street);
        this.tenantName = new TenantName(tenantName);
    }

    public Reservation(CreateReservationCommand command) {
        this(
                command.startDate().toLocalDate(), // Convert java.sql.Date to LocalDate
                command.endDate().toLocalDate(),   // Convert java.sql.Date to LocalDate
                command.street(),
                command.tenantName()
        );
    }

    public Reservation updateInformation(LocalDate startDate, LocalDate endDate, String street, String tenantName) {
        validateDateDifference(startDate, endDate);
        this.startDate = Date.valueOf(startDate); // Convert LocalDate to java.sql.Date
        this.endDate = Date.valueOf(endDate);     // Convert LocalDate to java.sql.Date
        this.address = new TenantAddress(street);
        this.tenantName = new TenantName(tenantName);
        return this;
    }

    // Default constructor
    public Reservation() {
    }

    // Validation for date difference
    private void validateDateDifference(LocalDate startDate, LocalDate endDate) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);
        if (daysDifference < 1 || daysDifference > 2) {
            throw new IllegalArgumentException("The difference between startDate and endDate must be between 1 and 2 days.");
        }
    }

    public String getAddress() {
        return address.street();
    }

    public String getTenantName() {
        return tenantName.tenantName();
    }

}
