package com.co.flypass.gestioninventario.application.category;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.co.flypass.gestioninventario.domain.cateogry.CategoryRepository;
import com.co.flypass.gestioninventario.exception.NoDataFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public void existCategory(long id){

        if(categoryRepository.findCategoryById(id).isEmpty()){
            throw new NoDataFoundException("Categoría no encontrada");
        }
    }
}