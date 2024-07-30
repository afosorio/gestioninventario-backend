package com.co.flypass.gestioninventario.infrastructure.persistence.cateogry;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.co.flypass.gestioninventario.domain.cateogry.CategoryRepository;
import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.infrastructure.persistence.customer.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryImpl extends CrudRepository<CategoryEntity, Long>, CategoryRepository {


    @Override
    default void save(Category category) {
        save(CategoryEntity.fromDomain(category));
    }
}
