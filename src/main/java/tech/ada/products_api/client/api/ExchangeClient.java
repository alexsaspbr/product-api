package tech.ada.products_api.client.api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tech.ada.products_api.client.api.dto.ExchangeDTO;
import tech.ada.products_api.client.api.dto.ExchangeResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ExchangeClient {

    //chamada para api externa
    public BigDecimal getExchange() {
        RestTemplate restTemplate = new RestTemplate();
        ExchangeResponseDTO response = restTemplate.getForObject("https://economia.awesomeapi.com.br/json/last/USD-BRL", ExchangeResponseDTO.class);
        return response.getExchange().getBid();
    }

    public BigDecimal getExchangeAvgForPeriod(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
        RestTemplate restTemplate = new RestTemplate();
        String startDateS = formater.format(startDate);
        String endDateS = formater.format(endDate);
        ResponseEntity<List<ExchangeDTO>> exchanges = restTemplate.exchange(
                "https://economia.awesomeapi.com.br/json/daily/USD-BRL/?start_date={startDate}&end_date={endDate}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ExchangeDTO>>() {
                },
                startDateS,
                endDateS);
        int size = exchanges.getBody().size();
        return exchanges.getBody().stream()
                .map(ExchangeDTO::getBid)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(size));
    }



}
