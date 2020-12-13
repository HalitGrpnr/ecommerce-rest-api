package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.RatingDto;

import java.util.List;

public interface RatingService {
    RatingDto get(Long id);
    RatingDto add(RatingDto ratingDto);
    RatingDto update(RatingDto ratingDto);
    void delete(Long id);
    List<RatingDto> getAll();
}
