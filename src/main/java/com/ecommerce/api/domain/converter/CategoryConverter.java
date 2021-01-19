package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryConverter {

    public CategoryDto convert(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    public Category convert(CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setParentId(categoryDto.getParentId());
        category.setName(categoryDto.getName());

        return category;
    }


    public List<CategoryDto> convert(List<Category> categories) {
        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}