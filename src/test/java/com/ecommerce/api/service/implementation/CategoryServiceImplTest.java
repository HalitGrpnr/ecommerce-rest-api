package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CategoryConverter;
import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.entity.Category;
import com.ecommerce.api.repository.CategoryRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryConverter categoryConverter;
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDto dto;

    private static final Long ID = 1L;
    private static final Long INVALID_ID = 2L;
    private static final String FOOD = "food";
    private static final String CLOTHES = "clothes";
    private final List<Category> categories = Collections.singletonList(category);
    private final List<CategoryDto> categoryDtos = Collections.singletonList(dto);
    private final List<Long> idList = Collections.singletonList(ID);

    @BeforeEach
    public void setup() {
        categoryRepository = mock(CategoryRepository.class);
        categoryConverter = mock(CategoryConverter.class);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryConverter);

        dto = CategoryDto.builder().id(ID).name(FOOD).build();
        category = Category.builder().id(ID).name(FOOD).build();

        when(categoryConverter.convert(any(CategoryDto.class))).thenReturn(category);
        when(categoryConverter.convert(any(Category.class))).thenReturn(dto);
    }

    @Test
    void add() {
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDto categoryDto = categoryService.add(CategoryDto.builder().build());
        assertNotNull(categoryDto);
        assertNotNull(categoryDto.getId());
        verify(categoryRepository, times(1)).save(any());
        verify(categoryConverter, times(1)).convert(any(CategoryDto.class));
        verify(categoryConverter, times(1)).convert(any(Category.class));
        verifyNoMoreInteractions(categoryRepository);
        verifyNoMoreInteractions(categoryConverter);
    }

    @Test
    void whenIdValid_thenReturnCategory() {
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));

        CategoryDto categoryDto = categoryService.get(ID);

        assertNotNull(categoryDto);
        assertEquals(ID, categoryDto.getId());

        verify(categoryRepository, times(1)).findById(any());
        verify(categoryConverter, times(1)).convert(any(Category.class));
        verifyNoMoreInteractions(categoryRepository);
        verifyNoMoreInteractions(categoryConverter);
    }

    @Test()
    void whenIdInValid_thenThrowsException() {
        when(categoryRepository.findById(INVALID_ID)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> categoryService.get(INVALID_ID));
    }

    @Test
    void update() {
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDto updatedDto = CategoryDto.builder().id(ID).name(CLOTHES).build();
        categoryService.update(updatedDto);

        verify(categoryRepository, times(1)).save(any());
        verify(categoryConverter, times(1)).convert(any(CategoryDto.class));
        verify(categoryConverter, times(1)).convert(any(Category.class));
        verifyNoMoreInteractions(categoryRepository);
        verifyNoMoreInteractions(categoryConverter);
    }

    @Test
    void delete() {
        doNothing().when(categoryRepository).deleteById(any());
        categoryService.delete(ID);
        verify(categoryRepository, times(1)).deleteById(any());
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    void getAll_returnEmpty() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class, () -> categoryService.getAll());
    }

    @Test
    void getAll() {
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDto> list = categoryService.getAll();
        assertFalse(list.isEmpty());
        assertEquals(categories.size(), list.size());

    }

    @Test
    void findByIds() {
        when(categoryRepository.findCategoriesByIdIn(idList)).thenReturn(categories);
        List<Category> list = categoryService.findByIds(idList);

        assertFalse(list.isEmpty());
        assertEquals(categories.size(), list.size());
    }

    @Test
    void getByIds() {
        when(categoryRepository.findCategoriesByIdIn(idList)).thenReturn(categories);
        when(categoryConverter.convert(anyList())).thenReturn(categoryDtos);
        List<CategoryDto> list = categoryService.getByIds(idList);

        assertFalse(list.isEmpty());
        assertEquals(categories.size(), list.size());
        verify(categoryConverter, times(1)).convert(anyList());
    }


}