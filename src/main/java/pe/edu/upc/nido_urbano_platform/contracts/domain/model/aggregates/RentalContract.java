package pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.PropertyId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.UserId;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Entity
@Table(name = "rental_contract")
@Getter
@Setter
@NoArgsConstructor
public class RentalContract extends AuditableAbstractAggregateRoot<RentalContract> {

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "propertyId", column = @Column(name = "property_id", nullable = false))
    })
    private PropertyId propertyId;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))
    })
    private UserId userId;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "landLordId", column = @Column(name = "landLord_id", nullable = false))
    })
    private LandlordId landlordId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "rent", nullable = false)
    private Double rent;

    @Column(name = "payment_frequency", nullable = false)
    private String paymentFrequency;

    @Column(name = "deposit_amount", nullable = false)
    private Double depositAmount;

    @Column(name = "termination_fee")
    private Double terminationFee;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Embedded
    private Term terms;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    //Create
    public RentalContract(CreateRentalContractCommand command){
        this.propertyId = new PropertyId(command.propertyId());
        this.userId = new UserId(command.userId());
        this.landlordId = new LandlordId(command.landlordId());
        this.status = command.status();
        this.rent = command.rent();
        this.depositAmount = command.depositAmount();
        this.terminationFee = command.terminationFee();
        this.paymentMethod = command.paymentMethod();
        this.terms = new Term();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
    }
    //Update
    public void updateRentalDetails(String Status, Double rent, String paymentFrequency, Double depositAmount, Double terminationFee, String paymentMethod,Term terms) {
        this.status = status;
        this.rent = rent;
        this.paymentFrequency = paymentFrequency;
        this.depositAmount = depositAmount;
        this.terminationFee = terminationFee;
        this.paymentMethod = paymentMethod;
        this.terms = terms;
    }
    //Delete



}
