package pe.edu.upc.nido_urbano_platform.contracts.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "house_modality", nullable = false)
    private String houseModality; // "rental" o "sale"

    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private double price;

    public Property() {}

    public Property(String name, String houseModality, double price) {
        this.name = name;
        this.houseModality = houseModality;
        this.price = price;
    }
}

