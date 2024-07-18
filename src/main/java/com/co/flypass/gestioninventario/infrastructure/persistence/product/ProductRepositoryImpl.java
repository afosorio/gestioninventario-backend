package com.co.flypass.gestioninventario.infrastructure.persistence.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryImpl extends ListCrudRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity>, ProductRepository {

    @Override
    default void save(Product employee) {
        save(ProductEntity.fromDomain(employee));
    }

    @Override
    default void update(Product employee) {
        save(employee);
    }

    @Override
    default void delete(long id) {
         deleteById(id);
    }

    @Override
    default Optional<Product> findProductById(long id) {
        return findById(id).map(ProductEntity::toDomain);
    }

    @Override
    default List<Product> findAllProducts() {
        return findAll().stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    default Optional<List<Product>> findProducts(Category category, LocalDate startDate, LocalDate endDate) {

        Specification<ProductEntity> specification = ProductSpecification.get(category, startDate, endDate);
        List<ProductEntity> listEntity = findAll(specification);
        List<Product> listDomain = listEntity.stream().map(ProductEntity::toDomain).toList();

        if (listDomain.isEmpty()) return Optional.empty();
        return Optional.of(listDomain);
    }
}