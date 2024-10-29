package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.model.Product;
import tech.ada.products_api.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO criar(ProductDTO productDTO) {

        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());

        productRepository.save(product);

        return productDTO;

    }

    public List<ProductDTO> listAll() {
        return this.productRepository.findAll().stream()
                .map(this::convert).collect(Collectors.toList());
    }

    private ProductDTO convert (Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(product.getSku());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        return productDTO;
    }

    public ProductDTO buscarPorSku(String sku) {
        Optional<Product> optionalProduct = getBySku(sku);
        if(optionalProduct.isPresent())
            return convert(optionalProduct.get());
        return null;
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

    public void delete(String sku) {

    }

}
