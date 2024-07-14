package com.co.flypass.gestioninventario.infrastructure.persistence.cateogry;

import com.co.flypass.gestioninventario.domain.cateogry.CategoryRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryImpl extends CrudRepository<CategoryEntity, Long>, CategoryRepository {
}
