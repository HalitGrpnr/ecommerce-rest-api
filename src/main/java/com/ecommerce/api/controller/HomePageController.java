package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.dto.FavoriteDto;
import com.ecommerce.api.domain.dto.HomePageDto;
import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.FavoriteService;
import com.ecommerce.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/home")
public class HomePageController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final FavoriteService favoriteService;

    public HomePageController(CategoryService categoryService, ProductService productService, FavoriteService favoriteService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<HomePageDto> getHomePageData(@PathVariable("userId") Long userId){
        try {
            List<CategoryDto> categories = categoryService.getAll();
            List<ProductDto> products = productService.getHomePageData();
            FavoriteDto userFavorite = favoriteService.getByUserId(userId);
            List<Long> productIds = userFavorite.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());

            for (ProductDto productDto : products) {
                if (productIds.contains(productDto.getId())) {
                    productDto.setLiked(true);
                }
            }

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
