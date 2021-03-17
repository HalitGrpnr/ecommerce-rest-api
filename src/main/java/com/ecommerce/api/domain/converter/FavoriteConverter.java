package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.FavoriteDto;
import com.ecommerce.api.domain.entity.Favorite;
import org.springframework.stereotype.Service;

@Service
public class FavoriteConverter {

    private ProductConverter productConverter;
    private UserConverter userConverter;

    public FavoriteConverter(ProductConverter productConverter, UserConverter userConverter) {
        this.productConverter = productConverter;
        this.userConverter = userConverter;
    }

    public FavoriteDto convert(Favorite favorite) {
        if (favorite == null) {
            return null;
        }
        FavoriteDto favoriteDto = new FavoriteDto();

        favoriteDto.setId(favorite.getId());
        favoriteDto.setUserDto(userConverter.convert(favorite.getUser()));
        favoriteDto.setProducts(productConverter.convert(favorite.getProducts()));

        return favoriteDto;
    }

    public Favorite convert(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();

        favorite.setId(favoriteDto.getId());

        return favorite;
    }
}
