package com.co.flypass.gestioninventario.application;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public void updateProduct(long productId, double price, int quantity) {

        Optional<Product> productOptional = productRepository.findProductById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStockQuantity(quantity);
            product.setPrice(price);
            productRepository.update(product);
        }
    }

    public void deleteProduct(long productId) {

        Optional<Product> productOptional = productRepository.findProductById(productId);
        if (productOptional.isPresent()) {
            productRepository.delete(productId);
        }
    }

}
