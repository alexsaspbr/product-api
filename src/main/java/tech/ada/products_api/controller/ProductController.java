package tech.ada.products_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    static List<ProductDTO> products = new ArrayList<>();

    @Operation(summary = "Create products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "422", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDTO.class))
                    }
            )

    })
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        //validar produto
        //gravar na db
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.criar(productDTO));
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
