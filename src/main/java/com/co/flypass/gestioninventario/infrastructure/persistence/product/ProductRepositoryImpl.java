package com.co.flypass.gestioninventario.infrastructure.persistence.product;

import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryImpl extends CrudRepository<ProductEntity, Long>, ProductRepository {
}
