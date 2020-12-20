package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.RatingConverter;
import com.ecommerce.api.domain.dto.RatingDto;
import com.ecommerce.api.domain.entity.Rating;
import com.ecommerce.api.repository.RatingRepository;
import com.ecommerce.api.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    
    private RatingRepository ratingRepository;
    private RatingConverter ratingConverter;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingConverter ratingConverter) {
        this.ratingRepository = ratingRepository;
        this.ratingConverter = ratingConverter;
    }

    @Override
    public RatingDto get(Long id) {
        Rating rating = ratingRepository.findById(id).orElse(new Rating());
        RatingDto ratingDto = ratingConverter.convertToDto(rating);
        return ratingDto;
    }

    @Override
    public RatingDto add(RatingDto ratingDto) {
        Rating rating = ratingConverter.convertToEntity(ratingDto);
        rating = ratingRepository.save(rating);

        return ratingConverter.convertToDto(rating);
    }

    @Override
    public RatingDto update(RatingDto ratingDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public List<RatingDto> getAll() {
        List<Rating> ratings = ratingRepository.findAll();
        List<RatingDto> ratingDtos = ratings.stream().map(c -> ratingConverter.convertToDto(c)).collect(Collectors.toList());
        return ratingDtos;
    }
}
