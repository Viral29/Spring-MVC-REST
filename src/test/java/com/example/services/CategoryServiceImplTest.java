package com.example.services;

import com.example.api.v1.mapper.CategoryMapper;
import com.example.api.v1.model.CategoryDTO;
import com.example.domain.Category;
import com.example.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    public static final String NAME = "test";
    public static final long ID = 4L;
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository,CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(),new Category(),new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertEquals(3,categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(ID,categoryDTO.getId().longValue());
        assertEquals(NAME,categoryDTO.getName());
    }
}