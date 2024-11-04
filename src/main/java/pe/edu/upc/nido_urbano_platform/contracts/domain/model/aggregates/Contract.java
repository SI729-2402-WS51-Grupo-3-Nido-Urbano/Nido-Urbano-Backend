package pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities.Property;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities.Tenant;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities.Landlord;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;
import java.util.regex.PatternSyntaxException;

@Entity
@Table(name = "profiles")
public class Contract extends AuditableAbstractAggregateRoot<Contract> {

    @Getter
    @Setter
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;

    @Getter
    @Setter
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    @Getter
    @Setter
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "landlord_id", referencedColumnName = "id")
    private Landlord landlord;

    @Getter
    @Setter
    @NotNull
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Getter
    @Setter
    @NotNull
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Getter
    @Setter
    @Column(name = "status", nullable = false)
    private String status; // e.g., "pending", "active", "canceled"

    @Embedded
    private Term terms;

    public Contract(Long aLong, Long aLong1, Long aLong2, Double price, Term terms, Date date, Date date1, String pending) {
        super();
    }

    public Contract(@NotNull Long aLong, @NotNull Long aLong1, @NotNull Long aLong2, @NotNull Double price, @NotNull String terms, @NotNull Date date, @NotNull Date date1, String pending) {

    }

    public Contract() {

    }

    public void updateContractDetails(double price, Term terms, Date date, Date date1) {

    }

    public PatternSyntaxException getTerms() {
        return null;
    }
}
