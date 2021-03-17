package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.FavoriteDto;
import com.ecommerce.api.domain.entity.Favorite;
import com.ecommerce.api.domain.request.FavoriteAddProductRequest;

import java.util.List;

public interface FavoriteService {
    FavoriteDto get(Long id);
    FavoriteDto getByUserId(Long userId);
    FavoriteDto add(FavoriteAddProductRequest request);
    Favorite save(Favorite favorite);
    FavoriteDto update(FavoriteAddProductRequest request);
    void delete(Long id);
    List<FavoriteDto> getAll();
}
