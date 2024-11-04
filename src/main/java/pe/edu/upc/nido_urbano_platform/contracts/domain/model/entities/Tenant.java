package pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "dni", nullable = false)
    private Integer dni;

    public Tenant() {}

    public Tenant(String name, Integer dni) {
        this.name = name;
        this.dni = dni;
    }

    public Object getId() {
        return null;
    }
}

