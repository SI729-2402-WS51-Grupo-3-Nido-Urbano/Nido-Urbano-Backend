package pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.PropertyId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.UserId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Terms;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Entity
@Table(name = "purchase_contract")
@Getter
@Setter
@NoArgsConstructor
public class PurchaseContract extends AuditableAbstractAggregateRoot<PurchaseContract> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "property_id", nullable = false))
    })
    private PropertyId propertyId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false))
    })
    private UserId userId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "landlord_id", nullable = false))
    })
    private LandlordId landlordId;

    @Column(name = "status")
    private String status;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "downPayment")
    private String downPayment;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "terms", nullable = false))
    })
    private Terms terms;

    @Column(name = "agreedTerms")
    private Boolean agreedTerms;

    @Column(name = "closing_date")
    private Date closingDate;

    @Column(name = "transferCostsIncluded")
    private Boolean transferCostsIncluded;

    // Create
    public PurchaseContract(CreatePurchaseContractCommand command) {
        this.propertyId = new PropertyId(command.propertyId());
        this.userId = new UserId(command.userId());
        this.landlordId = new LandlordId(command.landlordId());
        this.status = command.status();
        this.purchasePrice = command.purchasePrice();
        this.paymentMethod = command.paymentMethod();
        this.terms = new Terms((command.terms()));
        this.agreedTerms = command.agreedTerms();
        this.closingDate = command.closingDate();
        this.transferCostsIncluded = command.transferCostsIncluded();
    }

    // Update
    public void updatePurchaseDetails(String status, Double purchasePrice, String paymentMethod, Date closingDate, Boolean agreedTerms) {
        this.status = status;
        this.purchasePrice = purchasePrice;
        this.paymentMethod = paymentMethod;
        this.closingDate = closingDate;
        this.terms = terms;
        this.agreedTerms = agreedTerms;
    }

    // Delete
}

