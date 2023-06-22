package com.example.bloggingaplication.controllers;

import com.example.bloggingaplication.payloads.ApiResponse;
import com.example.bloggingaplication.payloads.CategoryDto;
import com.example.bloggingaplication.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired private CategoryService categoryService;

    @PostMapping("/")
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto cate = this.categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(cate, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    private ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CategoryDto categoryDto,
            @PathVariable("categoryId") Integer categoryId ){
        CategoryDto updated = this.categoryService.updateCategory(categoryDto,categoryId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{categoryId}")
    private ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryID){
        this.categoryService.deleteCategory(categoryID);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted succesfully!", true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> listAllCategory(){
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> getUserById(@PathVariable("categoryID") Integer categoryID){
        return ResponseEntity.ok(this.categoryService.getCategoryByID(categoryID));
    }
}
