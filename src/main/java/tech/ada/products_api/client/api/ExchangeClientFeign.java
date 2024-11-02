package tech.ada.products_api.client.api;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tech.ada.products_api.client.api.dto.ExchangeResponseDTO;

@FeignClient(name = "exchange-client", url = "https://economia.awesomeapi.com.br")
public interface ExchangeClientFeign {

    @GetMapping("/json/last/USD-BRL")
    ResponseEntity<ExchangeResponseDTO> getExchange();

}
