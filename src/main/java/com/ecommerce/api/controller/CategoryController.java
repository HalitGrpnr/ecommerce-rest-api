package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.add(categoryDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
    }

}
