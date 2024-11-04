package pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "landlords")
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Getter
    @Setter
    @Column(name = "signature", nullable = true)
    private String signature;

    @Getter
    @Setter
    @Column(name = "dni", nullable = false)
    private Integer dni;

    public Landlord() {}

    public Landlord(String name, String contactEmail, String signature, Integer dni) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.signature = signature;
        this.dni = dni;
    }

    public Object getId() {
        return null;
    }
}

