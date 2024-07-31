package com.co.flypass.gestioninventario.domain.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    void update(Product product);

    void delete(long id);

    Optional<Product> findProductById(long id);

    List<Product> findAllProducts();

    Optional<List<Product>> findProducts(final int category, final LocalDate startDate, final LocalDate endDate);
}