package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.ProductService;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.exception.NoDataFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") long id) throws NoDataFoundException {
        Product product = productService.getProductById(id).orElseThrow(() -> new NoDataFoundException("Product not found"));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
