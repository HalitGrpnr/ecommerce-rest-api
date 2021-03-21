package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        try {
            return ResponseEntity.ok(categoryService.getAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(categoryService.get(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDto> add(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.add(categoryDto));
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
