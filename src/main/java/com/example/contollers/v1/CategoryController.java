package com.example.contollers.v1;

import com.example.api.v1.model.CategoryDTO;
import com.example.api.v1.model.CategoryListDTO;
import com.example.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getallCategories(){

        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),HttpStatus.OK);
    }

    @GetMapping("{Name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String Name) {

        return new ResponseEntity<CategoryDTO>(
                categoryService.getCategoryByName(Name),
                HttpStatus.OK
        );
    }

}
