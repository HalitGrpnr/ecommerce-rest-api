package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.dto.HomePageDto;
import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/api/home")
public class HomePageController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public HomePageController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<HomePageDto> getHomePageData(){
        try {
            List<CategoryDto> categories = categoryService.getAll();
            List<ProductDto> products = productService.getHomePageData();
            HomePageDto dto = HomePageDto.builder()
                    .categories(categories)
                    .products(products)
                    .build();
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
