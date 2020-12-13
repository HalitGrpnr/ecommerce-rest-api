package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto get(Long id);
    CategoryDto add(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto);
    void delete(Long id);
    List<CategoryDto> getAll();
}
