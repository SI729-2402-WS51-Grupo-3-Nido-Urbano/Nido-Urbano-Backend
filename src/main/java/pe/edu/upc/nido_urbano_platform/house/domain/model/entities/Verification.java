package pe.edu.upc.nido_urbano_platform.house.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.nido_urbano_platform.house.domain.model.valueobjects.UniqueRegistrationNumber;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
@Table(name="verifications")
public class Verification extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name="condition", nullable = false)
    private String condition;

    @Column(name="unique_registration_number")
    private UniqueRegistrationNumber uniqueRegistrationNumber;

    public Verification() {
    }

    public Verification(String ownerName, String condition, UniqueRegistrationNumber uniqueRegistrationNumber) {
        this.ownerName = ownerName;
        this.condition = condition;
        this.uniqueRegistrationNumber = uniqueRegistrationNumber;
    }
}
