package com.example.contollers.v1;

import com.example.api.v1.model.CategoryDTO;
import com.example.api.v1.model.CategoryListDTO;
import com.example.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories/";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getallCategories(){

        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{Name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String Name) {

        return categoryService.getCategoryByName(Name);
    }

}
