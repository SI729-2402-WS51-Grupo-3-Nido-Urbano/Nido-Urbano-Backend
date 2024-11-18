package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.ReservationId;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "houses")
public class House extends AuditableAbstractAggregateRoot<House> {

    @Getter
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "houseCode", column = @Column(name = "code", length = 36, nullable = false))
    })
    private final HouseCode houseCode;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "reservationId", column = @Column(name = "reservation_id", nullable = false))
    })
    private ReservationId reservationId;

    //---------------------------------------------------
    public House() {
        this.houseCode = new HouseCode();
    }

    public House(Long reservationId) {
        this();
        this.reservationId = new ReservationId(reservationId);
    }

    public House(ReservationId reservationId) {
        this();
        this.reservationId = reservationId;
    }

    public Long getReservationId() {
        return reservationId.reservationId();
    }
}
