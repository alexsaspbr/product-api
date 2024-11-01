package tech.ada.products_api.client.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class ExchangeClient {

    //chamada para api externa
    public BigDecimal getExchange() {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeResponseDTO response = restTemplate.getForObject("https://economia.awesomeapi.com.br/json/last/USD-BRL", ExchangeResponseDTO.class);
        return response.getExchange().getBid();
    }



}
