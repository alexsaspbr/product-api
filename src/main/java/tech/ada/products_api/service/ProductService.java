package tech.ada.products_api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ada.products_api.client.api.ExchangeClient;
import tech.ada.products_api.client.api.ExchangeClientFeign;
import tech.ada.products_api.client.api.dto.ExchangeResponseDTO;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.dto.ResponseDTO;
import tech.ada.products_api.model.Product;
import tech.ada.products_api.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ExchangeClientFeign exchangeClientFeign;

    public ProductDTO criar(ProductDTO productDTO) {

        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setRegisterDate(productDTO.getRegisterDate());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());

        //BigDecimal exchange = exchangeClient.getExchange();

        //BigDecimal exchange = exchangeClient.getExchangeAvgForPeriod(LocalDate.now(), LocalDate.now());

        ResponseEntity<ExchangeResponseDTO> response = exchangeClientFeign.getExchange();
        if(response.getStatusCode().value() == 200) {
            BigDecimal exchange = response.getBody().getExchange().getBid();
            product.setExchange(exchange);
            product.setPriceConverted(productDTO.getPrice().multiply(exchange));
        }

        productRepository.save(product);

        return productDTO;

    }

    public List<ProductDTO> listAll() {
        return this.productRepository.findAll().stream()
                .map(this::convert).collect(Collectors.toList());
    }


    public List<ProductDTO> listAll(LocalDateTime from, LocalDateTime to) {
        return this.productRepository.findByRegisterDate(from, to)
                .stream().map(this::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> listAll(String name, LocalDateTime from, LocalDateTime to) {
        //TODO - implementar com @Query
        return null;
    }

    private ProductDTO convert (Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(product.getSku());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setRegisterDate(product.getRegisterDate());
        productDTO.setPrice(product.getPrice());
        productDTO.setPriceConverted(product.getPriceConverted());
        productDTO.setExchange(product.getExchange());
        productDTO.setWeight(product.getWeight());
        return productDTO;
    }

    public ResponseDTO<?> buscarPorSku(String sku) {
        Optional<Product> optionalProduct = getBySku(sku);
        if(optionalProduct.isPresent())
            return ResponseDTO.builder().message("Produto encontrado")
                                            .timestamp(LocalDateTime.now())
                    .data(convert(optionalProduct.get())).build();
        return ResponseDTO.builder().message("Nenhum produto encontrado")
                .timestamp(LocalDateTime.now())
                .build();
    }

    private Optional<Product> getBySku(String sku) {
        return this.productRepository.findBySku(sku);
    }

    public List<ProductDTO> listAllNameEqualTo(String name) {
        return this.productRepository.findByNameContainingIgnoreCase(name)
                .stream().map(this::convert).collect(Collectors.toList());
    }

    public ProductDTO update(ProductDTO productDTO) {

        Optional<Product> optionalProduct = this.getBySku(productDTO.getSku());

        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setSku(productDTO.getSku());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setWeight(productDTO.getWeight());
            this.productRepository.save(product);
            return productDTO;
        }

        return null;
    }

    @Transactional
    public void delete(String sku) {
        this.productRepository.deleteBySku(sku);
    }

}
