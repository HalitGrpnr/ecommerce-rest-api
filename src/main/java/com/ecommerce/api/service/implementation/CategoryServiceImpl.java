package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CategoryConverter;
import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.entity.Category;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public CategoryDto get(Long id) {
        Category category = categoryRepository.findById(id).orElse(new Category());
        return categoryConverter.convert(category);
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = categoryConverter.convert(categoryDto);
        category = categoryRepository.save(category);

        return categoryConverter.convert(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Category category = categoryRepository.save(categoryConverter.convert(categoryDto));
        return categoryConverter.convert(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();

        if (CollectionUtils.isEmpty(categories)) {
            throw new EntityNotFoundException();
        }

        return categories.stream().map(categoryConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getByIds(List<Long> ids) {
        return categoryConverter.convert(findByIds(ids));
    }

    public List<Category> findByIds(List<Long> ids) {
        List<Category> categories = categoryRepository.findCategoriesByIdIn(ids);

        if (CollectionUtils.isEmpty(categories)) {
            throw new EntityNotFoundException();
        }

        return categories;
    }
}
