package com.example.bloggingaplication.services.implement;

import com.example.bloggingaplication.entity.Category;
import com.example.bloggingaplication.exceptions.ResourceNotFoundException;
import com.example.bloggingaplication.payloads.CategoryDto;
import com.example.bloggingaplication.repositories.CategoryRepository;
import com.example.bloggingaplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ModelMapper modelMapper;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return this.modelMapper.map(this.categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        this.categoryRepository.save(category);
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryByID(Integer categoryId) {
        return this.modelMapper.map(this.categoryRepository
                .findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId)), CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        this.categoryRepository.delete(category);
    }
}
