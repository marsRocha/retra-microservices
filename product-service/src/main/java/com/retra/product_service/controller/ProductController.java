package com.retra.product_service.controller;

import com.retra.product_service.dto.ProductSetDTO;
import com.retra.product_service.dto.ProductWithDetails;
import com.retra.product_service.model.Product;
import com.retra.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    private static final String PRODUCT_CREATE_LOG = "Product was successfully created";
    private static final String PRODUCT_EDIT_LOG = "Product {} was edited";
    private static final String PRODUCT_DELETE_LOG = "Product {} was deleted";

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductSetDTO productSetDTO) {
        productService.createProduct(productSetDTO);
        log.info(PRODUCT_CREATE_LOG);
    }

    @PutMapping(path = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void editProduct(@RequestBody ProductSetDTO productSetDTO, @PathVariable Long productId) {
        productService.editProduct(productSetDTO, productId);
        log.info(PRODUCT_EDIT_LOG, productId);
    }

    @DeleteMapping(path="/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        log.info(PRODUCT_DELETE_LOG, productId);
    }

    @GetMapping(path = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping(path = "/{productId}/detailed")
    @ResponseStatus(HttpStatus.OK)
    public ProductWithDetails getProductWithDetails(@PathVariable Long productId) {
        return productService.getProductWithDetails(productId);
    }
}
