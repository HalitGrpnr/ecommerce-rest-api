package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.RatingDto;
import com.ecommerce.api.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/ratings")
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<RatingDto>> getAll(){
        return ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDto> getRating(@PathVariable("id") Long id){
        return ResponseEntity.ok(ratingService.get(id));
    }

    @PostMapping
    public ResponseEntity<RatingDto> addRating(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(ratingService.add(ratingDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id){
        ratingService.delete(id);
    }

}
