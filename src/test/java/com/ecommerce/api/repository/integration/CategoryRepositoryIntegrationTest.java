package com.ecommerce.api.repository.integration;

import com.ecommerce.api.domain.entity.Category;
import com.ecommerce.api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class CategoryRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @Test
    public void whenIdValid_thenReturnCategory() {
        //given
        category = Category.builder().name("food").build();
        entityManager.persist(category);
        entityManager.flush();

        // when
        List<Category> categories = categoryRepository.findCategoriesByIdIn(Collections.singletonList(category.getId()));

        // then
        assertFalse(CollectionUtils.isEmpty(categories));
        assertEquals(1, categories.size());
        assertEquals(category.getName(), categories.get(0).getName());
    }

    @Test
    public void whenIdInvalid_thenThrowEntityNotFoundException() {
        List<Category> categories = categoryRepository.findCategoriesByIdIn(Collections.singletonList(2L));
        assertEquals(0, categories.size());
    }
}
