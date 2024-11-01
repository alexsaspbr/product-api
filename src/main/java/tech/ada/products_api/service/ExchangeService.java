package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.products_api.client.api.ExchangeClient;

import java.math.BigDecimal;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeClient exchangeClient;

    public BigDecimal getConvertedPrice(BigDecimal price) {
        return price.multiply(exchangeClient.getExchange());
    }

}
