package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryConverter {

    public CategoryDto convert(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .parentId(category.getParentId())
                .name(category.getName())
                .build();

        return categoryDto;
    }

    public Category convert(CategoryDto categoryDto) {
        Category category = Category.builder().build();

        category.setId(categoryDto.getId());
        category.setParentId(categoryDto.getParentId());
        category.setName(categoryDto.getName());

        return category;
    }


    public List<CategoryDto> convert(List<Category> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptyList();
        }

        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}