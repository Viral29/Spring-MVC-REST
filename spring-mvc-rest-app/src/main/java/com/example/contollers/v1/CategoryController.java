package com.example.contollers.v1;

import com.example.api.v1.model.CategoryDTO;
import com.example.api.v1.model.CategoryListDTO;
import com.example.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is a Category Controller")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories/";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("Lists all the product category")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getallCategories(){

        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @ApiOperation("Get a Category by Name")
    @GetMapping("{Name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String Name) {

        return categoryService.getCategoryByName(Name);
    }

}
