package com.co.flypass.gestioninventario.infrastructure.persistence.cateogry;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.co.flypass.gestioninventario.domain.cateogry.CategoryRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepositoryImpl extends CrudRepository<CategoryEntity, Long>, CategoryRepository {


    @Override
    default void save(Category category) {
        save(CategoryEntity.fromDomain(category));
    }

    @Override
    default Optional<Category> findCategoryById(long id) {
        return findById(id).map(CategoryEntity::toDomain);
    }
}
