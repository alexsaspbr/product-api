package tech.ada.products_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;

}
