package pe.edu.upc.nido_urbano_platform.house_managers.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.valueobjects.*;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Getter
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

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "house_type", length = 20, nullable = false)
    private HouseType houseType;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "house_modal", length = 20, nullable = false)
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

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status_landlord", length = 20, nullable = false)
    private StatusLandlord statusLandlord;

    
    @Column(name="photo", nullable = false)
    private String photo;
    @Column(name="video", nullable = false)
    private String video;
    @Column(name="terms_conditions", length = 200, nullable = false)
    private String termsConditions;





    public House(){
    }

    public House(UserPropertyId userPropertyId, String houseName, AddressHouse address, HouseType houseType, HouseModal houseModal, Long price, int size, String description, Date publicationDate, int startsCalification, StatusLandlord statusLandlord, String photo, String video, String termsConditions) {
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
        this.termsConditions = termsConditions;

    }

    public House(CreateHouseCommand command) {
        this.userPropertyId = new UserPropertyId(command.userPropertyId());
        this.houseName = command.houseName();
        this.address = new AddressHouse(command.address());
        this.houseType = HouseType.valueOf(command.houseType());
        this.houseModal = HouseModal.valueOf(command.houseModal());
        this.price = command.price();
        this.size = command.size();
        this.description = command.description();
        this.publicationDate = command.publicationDate();
        this.startsCalification = command.startsCalification();
        this.statusLandlord = StatusLandlord.valueOf(command.statusLandlord());
        this.photo = command.photo();
        this.video = command.video();
        this.termsConditions = command.termsConditions();

    }

    public void updateInformation(Long userPropertyID, String houseName, String address, String houseType,
                                  String houseModal, Long price, int size, String description, Date publicationDate,
                                  int startsCalification, String statusLandlord, String photo, String video,String termsConditions) {
        this.userPropertyId = new UserPropertyId(userPropertyID);
        this.houseName = houseName;
        this.address = new AddressHouse(address);
        this.houseType = HouseType.valueOf(houseType);
        this.houseModal = HouseModal.valueOf(houseModal);
        this.price = price;
        this.size = size;
        this.description = description;
        this.publicationDate = publicationDate;
        this.startsCalification = startsCalification;
        this.statusLandlord = StatusLandlord.valueOf(statusLandlord);
        this.photo = photo;
        this.video= video;
        this.termsConditions = termsConditions;

    }


}
