package pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities.Property;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities.Tenant;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities.Landlord;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

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
}
