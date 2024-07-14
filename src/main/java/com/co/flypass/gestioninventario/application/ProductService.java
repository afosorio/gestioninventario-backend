package com.co.flypass.gestioninventario.application;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public ProductService(ProductRepository productRepository) {
    }

    public List<Product> getAllProducts() {
        return Collections.singletonList(new Product());
    }

    public Optional<Product> getProductById(long id) {
        return Optional.of(new Product());
    }

    public Product createProduct(Product product) {
        return new Product();
    }

    public Product updateProduct(Product product) {
        return new Product();
    }

    public void deleteProduct(long id) {

    }
}
