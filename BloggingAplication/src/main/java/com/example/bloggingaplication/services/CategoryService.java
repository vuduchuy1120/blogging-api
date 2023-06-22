package com.example.bloggingaplication.services;

import com.example.bloggingaplication.entity.Category;
import com.example.bloggingaplication.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public CategoryDto addCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    public CategoryDto getCategoryByID(Integer categoryId);
    public List<CategoryDto> getAllCategory();
    public void deleteCategory(Integer categoryId);

}
