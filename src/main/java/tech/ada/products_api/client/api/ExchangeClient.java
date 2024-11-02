package tech.ada.products_api.client.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ExchangeClient {

    //chamada para api externa
    public BigDecimal getExchange() {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeResponseDTO response = restTemplate.getForObject("https://economia.awesomeapi.com.br/json/last/USD-BRL", ExchangeResponseDTO.class);
        return response.getExchange().getBid();
    }

    public BigDecimal getExchangeAvgForPeriod(LocalDate startDate, LocalDate endDate) {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeResponseDTO response = restTemplate.getForObject("https://economia.awesomeapi.com.br/json/last/USD-BRL", ExchangeResponseDTO.class);
        return response.getExchange().getBid();
    }



}
