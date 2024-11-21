package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.valueobjects.*;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Getter
@Entity
@Table(name="reservations")
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    /**
     * Start Date
     */
    @NotNull
    @Column(name = "start_date", length = 40, nullable = false)
    private String startDate;

    /**
     * End Date
     */
    @NotNull
    @Column(name = "end_date", length = 50, nullable = false)
    private String endDate;

    /**
     * Tenant Address
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "tenantAddress", column = @Column(name = "tenant_address", length = 100, nullable = false))
    })
    private TenantAddress tenantAddress;

    /**
     * Tenant Name
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "tenantName", column = @Column(name = "tenant_name", length = 100, nullable = false))
    })
    private TenantName tenantName;


    /**
     * House Address
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseAddress", column = @Column(name = "house_address", length = 100, nullable = false))
    })
    private HouseAddress houseAddress;

    /**
     * House Name
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseName", column = @Column(name = "house_name", length = 100, nullable = false))
    })
    private HouseName houseName;

    /**
     * House ID
     */
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseId", column = @Column(name = "house_id", nullable = false))
    })
    private HouseId houseId;


    //---------------------------------------------------

    // Constructors
    public Reservation(String startDate,
                       String endDate,
                       String tenantAddress,
                       String tenantName,
                       String houseAddress,
                       String houseName,
                       Long houseId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tenantAddress = new TenantAddress(tenantAddress);
        this.tenantName = new TenantName(tenantName);
        this.houseAddress = new HouseAddress(houseAddress);
        this.houseName = new HouseName(houseName);
        this.houseId = new HouseId(houseId);

    }

    public Reservation(CreateReservationCommand command) {
        this(
                command.startDate(),
                command.endDate(),
                command.tenantAddress(),
                command.tenantName(),
                command.houseAddress(),
                command.houseName(),
                command.houseId()
        );
    }

    public void updateInformation(String startDate,
                                  String endDate,
                                  String tenantAddress,
                                  String tenantName,
                                  String houseAddress,
                                  String houseName,
                                  Long houseId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tenantAddress = new TenantAddress(tenantAddress);
        this.tenantName = new TenantName(tenantName);
        this.houseAddress = new HouseAddress(houseAddress);
        this.houseName = new HouseName(houseName);
        this.houseId = new HouseId(houseId);
    }

    /**
     * Default constructor
     */
    public Reservation() {
    }

    /**
     * Validation of startDate
     */
    public void validateStartDate(String startDate) {

    }

    /**
     * Validation of endDate
     */
    public void validateEndDate(String endDate) {

    }

}
