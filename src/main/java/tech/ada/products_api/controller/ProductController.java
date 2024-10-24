package tech.ada.products_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    static List<ProductDTO> products = new ArrayList<>();

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        //validar produto
        //gravar na db
        products.add(productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll() {
        return products;
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        ProductDTO produtoDB = products.stream()
                .filter(p -> productDTO.getSku().equalsIgnoreCase(p.getSku())).findFirst()
                .orElseThrow();
        int index = products.indexOf(produtoDB);
        produtoDB.setPrice(productDTO.getPrice());

        products.set(index, produtoDB);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoDB);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        ProductDTO produtoDB = products.stream()
                .filter(p -> sku.equalsIgnoreCase(p.getSku())).findFirst()
                .orElseThrow();

        products.remove(produtoDB);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
