package pe.edu.upc.nido_urbano_platform.house.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;
import pe.edu.upc.nido_urbano_platform.house.domain.model.valueobjects.*;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Entity
@Table(name = "houses")
public class House extends AuditableAbstractAggregateRoot<House> {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userPropertyId", column = @jakarta.persistence.Column(name = "user_property_id", nullable = false))
    })
    private UserPropertyId userPropertyId;

    @Column(name = "house_name", length = 70, nullable = false)
    private String houseName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "address", length = 100, nullable = false))
    })
    private AddressHouse address;

    @Column(name = "house_type", length = 10, nullable = false)
    private HouseType houseType;
    @Column(name = "house_modal", length = 10, nullable = false)
    private HouseModal houseModal;

    @Column(name = "price", nullable = false)
    private Long price;
    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @Column(name = "starts_calification", nullable = false)
    private int startsCalification;

    @Column(name = "status_landlord", length = 10, nullable = false)
    private StatusLandlord statusLandlord;

    @Column(name="photo", length = 100, nullable = false)
    private String photo;
    @Column(name="video", length = 100, nullable = false)
    private String video;

    @NotNull
    @OneToOne
    @JoinColumn(name = "verification_id")
    private Verification verification;

    public House(){
    }

    public House(UserPropertyId userPropertyId, String houseName, AddressHouse address, HouseType houseType, HouseModal houseModal, Long price, int size, String description, Date publicationDate, int startsCalification, StatusLandlord statusLandlord, String photo, String video, Verification verification) {
        this.userPropertyId = userPropertyId;
        this.houseName = houseName;
        this.address = address;
        this.houseType = houseType;
        this.houseModal = houseModal;
        this.price = price;
        this.size = size;
        this.description = description;
        this.publicationDate = publicationDate;
        this.startsCalification = startsCalification;
        this.statusLandlord = statusLandlord;
        this.photo = photo;
        this.video = video;
        this.verification = verification;
    }
}
