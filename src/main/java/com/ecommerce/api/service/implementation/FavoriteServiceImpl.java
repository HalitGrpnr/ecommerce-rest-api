package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.FavoriteConverter;
import com.ecommerce.api.domain.dto.FavoriteDto;
import com.ecommerce.api.domain.entity.Favorite;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.domain.entity.User;
import com.ecommerce.api.domain.request.FavoriteAddProductRequest;
import com.ecommerce.api.repository.FavoriteRepository;
import com.ecommerce.api.service.FavoriteService;
import com.ecommerce.api.service.ProductService;
import com.ecommerce.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteConverter favoriteConverter;
    private final ProductService productService;
    private final UserService userService;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, FavoriteConverter favoriteConverter, ProductService productService, UserService userService) {
        this.favoriteRepository = favoriteRepository;
        this.favoriteConverter = favoriteConverter;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public FavoriteDto get(Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return favoriteConverter.convert(favorite);
    }

    @Override
    public FavoriteDto getByUserId(Long userId) {
        Favorite favorite = favoriteRepository.findByUser_Id(userId);
        return favoriteConverter.convert(favorite);
    }

    @Override
    public FavoriteDto add(FavoriteAddProductRequest request) {
        List<Product> products = productService.getByIds(request.getProductIds());
        User user = userService.find(request.getUserId());
        Favorite favorite = new Favorite();
        favorite.setProducts(products);
        favorite.setUser(user);
        user.setFavorite(favorite);
        favorite = favoriteRepository.save(favorite);
        return favoriteConverter.convert(favorite);
    }

    public Favorite save(Favorite favorite) {
        favorite = favoriteRepository.save(favorite);
        return favorite;
    }

    @Override
    public FavoriteDto update(FavoriteAddProductRequest request) {
        Favorite favorite = favoriteRepository.findByUser_Id(request.getUserId());
        List<Product> products = productService.getByIds(request.getProductIds());
        favorite.setProducts(products);
        favorite = favoriteRepository.save(favorite);
        return favoriteConverter.convert(favorite);
    }

    @Override
    public void delete(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public List<FavoriteDto> getAll() {
        List<Favorite> favorites = favoriteRepository.findAll();

        if (CollectionUtils.isEmpty(favorites)) {
            throw new EntityNotFoundException();
        }

        return favorites.stream().map(favoriteConverter::convert).collect(Collectors.toList());
    }
}
