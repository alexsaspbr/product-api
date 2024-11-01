package tech.ada.products_api.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 10)
    private String sku;
    @Column(nullable = false)
    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDate;
    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal price;
    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal exchange;
    private Double weight;

}
