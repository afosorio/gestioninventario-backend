package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.product.ProductService;
import com.co.flypass.gestioninventario.domain.product.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Response<Object> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return new Response<>(HttpServletResponse.SC_OK, "Producto Creado");
    }

    @PutMapping
    public Mono<ResponseEntity<Object>> updateProduct(@RequestParam(required = false) final long productId,
                                                      @RequestParam(required = false) final double price,
                                                      @RequestParam(required = false) final int quantity) {

        return Mono.fromFuture(() -> productService.updatePriceAndQuantity(productId, price, quantity))
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @GetMapping("/all")
    public CompletableFuture<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteProduct(@PathVariable(value = "id") long id) {
        productService.deleteProduct(id);
        return new Response<>(HttpServletResponse.SC_OK, "Producto Eliminado");
    }

    @GetMapping("/products")
    public Response<Object> getProducts(int category, LocalDate startDate, LocalDate endDate) {
        return new Response<>(HttpServletResponse.SC_OK, "Productos Encontrados", productService.getProducts(category, startDate, endDate));
    }

}