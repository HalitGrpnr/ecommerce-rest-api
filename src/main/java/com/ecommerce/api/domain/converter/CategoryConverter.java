package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryConverter implements BaseConverter<CategoryDto, Category>{

    @Override
    public CategoryDto convertToDto (Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    @Override
    public Category convertToEntity (CategoryDto categoryDto) {
        Category category = new Category();

        category.setParentId(categoryDto.getParentId());
        category.setName(categoryDto.getName());

        return category;
    }
}