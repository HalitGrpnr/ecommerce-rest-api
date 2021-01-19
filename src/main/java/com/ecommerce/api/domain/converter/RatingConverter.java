package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.RatingDto;
import com.ecommerce.api.domain.entity.Rating;
import org.springframework.stereotype.Service;

@Service
public class RatingConverter {
    public RatingDto convert(Rating rating) {
        RatingDto ratingDto = new RatingDto();

        ratingDto.setId(rating.getId());
        ratingDto.setCreatedDate(rating.getCreatedDate());
        ratingDto.setRate(rating.getRate());

        return ratingDto;
    }

    public Rating convert(RatingDto ratingDto) {
        Rating rating = new Rating();

        rating.setId(ratingDto.getId());
        rating.setCreatedDate(ratingDto.getCreatedDate());
        rating.setRate(ratingDto.getRate());

        return rating;
    }
}
