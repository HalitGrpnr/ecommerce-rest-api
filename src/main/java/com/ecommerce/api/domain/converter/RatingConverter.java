package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.RatingDto;
import com.ecommerce.api.domain.entity.Rating;

public class RatingConverter implements BaseConverter<RatingDto, Rating> {
    @Override
    public RatingDto convertToDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();

        ratingDto.setId(rating.getId());
        ratingDto.setCreatedDate(rating.getCreatedDate());
        ratingDto.setRate(rating.getRate());

        return ratingDto;
    }

    @Override
    public Rating convertToEntity(RatingDto ratingDto) {
        Rating rating = new Rating();

        rating.setCreatedDate(ratingDto.getCreatedDate());
        rating.setRate(ratingDto.getRate());

        return rating;
    }
}
