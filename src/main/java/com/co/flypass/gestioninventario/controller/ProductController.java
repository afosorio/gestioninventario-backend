package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.product.ProductService;
import com.co.flypass.gestioninventario.domain.product.Product;
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

    @PostMapping
    public void createProduct(@RequestBody Product product) {
         productService.createProduct(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestParam(required = false) final long productId,
                                                 @RequestParam(required = false) final double price,
                                                 @RequestParam(required = false) final int quantity) {

        productService.updatePriceAndQuantity(productId, price, quantity);
        return ResponseEntity.ok(new Product());
    }

    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}