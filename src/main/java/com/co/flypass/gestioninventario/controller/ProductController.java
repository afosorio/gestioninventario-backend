package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.product.ProductService;
import com.co.flypass.gestioninventario.domain.product.Product;
import jakarta.servlet.http.HttpServletResponse;
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
    public Response<Object> createProduct(@RequestBody Product product) {
         productService.createProduct(product);
        return new Response<>(HttpServletResponse.SC_OK, "Producto Creado");
    }

    @PutMapping
    public Response<Object> updateProduct(@RequestParam(required = false) final long productId,
                                                 @RequestParam(required = false) final double price,
                                                 @RequestParam(required = false) final int quantity) {

        productService.updatePriceAndQuantity(productId, price, quantity);
        return new Response<>(HttpServletResponse.SC_OK, "Producto actualizado");
    }

    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteProduct(@PathVariable(value = "id") long id) {
        productService.deleteProduct(id);
        return new Response<>(HttpServletResponse.SC_OK, "Producto Eliminado");
    }
}