package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.dto.CategoryDto;
import com.ecommerce.api.domain.dto.FavoriteDto;
import com.ecommerce.api.domain.dto.HomePageDto;
import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.FavoriteService;
import com.ecommerce.api.service.HomePageService;
import com.ecommerce.api.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomePageServiceImpl implements HomePageService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final FavoriteService favoriteService;

    public HomePageServiceImpl(CategoryService categoryService, ProductService productService, FavoriteService favoriteService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.favoriteService = favoriteService;
    }

    @Override
    public HomePageDto getHomePageData(Long userId) {
        List<CategoryDto> categories = categoryService.getAll();
        List<ProductDto> products = productService.getHomePageData();
        FavoriteDto userFavorite = favoriteService.getByUserId(userId);

        if (userFavorite != null) {
            addUserFavorites(products, userFavorite);
        }

        return HomePageDto.builder()
                .categories(categories)
                .products(products)
                .build();
    }

    private void addUserFavorites(List<ProductDto> products, FavoriteDto userFavorite) {
        List<Long> productIds = userFavorite.getProducts().stream()
                .map(p -> p.getId())
                .collect(Collectors.toList());

        for (ProductDto productDto : products) {
            if (productIds.contains(productDto.getId())) {
                productDto.setLiked(true);
            }
        }
    }

    @Override
    public HomePageDto getHomePageData() {
        List<CategoryDto> categories = categoryService.getAll();
        List<ProductDto> products = productService.getHomePageData();

        return HomePageDto.builder()
                .categories(categories)
                .products(products)
                .build();
    }
}
