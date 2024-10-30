package tech.ada.products_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema
public class ProductDTO {
    
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime registerDate;
    private Double weight;

}
