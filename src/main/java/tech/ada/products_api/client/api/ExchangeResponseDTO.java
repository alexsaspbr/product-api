package tech.ada.products_api.client.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExchangeResponseDTO {

    @JsonProperty("USDBRL")
    private ExchangeDTO exchange;

}
