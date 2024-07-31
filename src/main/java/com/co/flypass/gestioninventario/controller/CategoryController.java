package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.category.CategoryService;
import com.co.flypass.gestioninventario.domain.cateogry.Category;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public Response<Object> save(@Valid @RequestBody Category category) {
        categoryService.save(category);
        return new Response<>(HttpServletResponse.SC_OK, "Categoría creada exitosamente");
    }
}