package tech.ada.products_api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema
public class ProductDTO {

    @Size(max = 10, message = "Tamanho maximo de 10 caracteres")
    @NotBlank(message = "O sku é obrigatório")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Somente letras")
    private String sku;
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @Parameter(required = true)
    private String description;
    private BigDecimal price;
    private BigDecimal priceConverted;
    private BigDecimal exchange;
    private LocalDateTime registerDate;
    private Double weight;


}
