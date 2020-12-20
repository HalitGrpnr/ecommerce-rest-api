package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CategoryConverter;
import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.entity.Category;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private CategoryRepository categoryRepository;
    private CategoryConverter categoryConverter = new CategoryConverter();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto get(Long id) {
        Category category = categoryRepository.findById(id).orElse(new Category());
        CategoryDto categoryDto = categoryConverter.convertToDto(category);
        return categoryDto;
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = categoryConverter.convertToEntity(categoryDto);
        category = categoryRepository.save(category);

        return categoryConverter.convertToDto(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(c -> categoryConverter.convertToDto(c)).collect(Collectors.toList());
        return categoryDtos;
    }
}
