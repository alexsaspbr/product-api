package tech.ada.products_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.products_api.dto.ProductDTO;
import tech.ada.products_api.dto.ResponseDTO;
import tech.ada.products_api.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService productService;

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
        return this.productService.listAll();
    }

    @GetMapping(value = "/all-from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll(LocalDateTime from, LocalDateTime to) {
        return this.productService.listAll(from, to);
    }

    @GetMapping(value = "/by-name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAll(@PathVariable("name") String name,
                                    @RequestHeader("from") LocalDateTime from,
                                    @RequestHeader("to")LocalDateTime to) {
        return this.productService.listAll(name, from, to);
    }

    @GetMapping(value = "/all-with-name-equalTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> listAllWithNameEqualTo(@RequestParam("name") String name) {
        return this.productService.listAllNameEqualTo(name);
    }

    @GetMapping(value = "/by/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<?>> bySKU(@PathVariable("sku") String sku) {
        return ResponseEntity.ok(this.productService.buscarPorSku(sku));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productService.update(productDTO));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        this.productService.delete(sku);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
