package tech.ada.products_api.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String sku;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal price;
    private Double weight;

}
