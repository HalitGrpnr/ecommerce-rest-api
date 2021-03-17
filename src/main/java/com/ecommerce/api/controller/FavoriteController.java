package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.FavoriteDto;
import com.ecommerce.api.domain.request.FavoriteAddProductRequest;
import com.ecommerce.api.service.FavoriteService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<List<FavoriteDto>> getAll(){
        try {
            return ResponseEntity.ok(favoriteService.getAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(favoriteService.get(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<FavoriteDto> getByUserId(@PathVariable("id") Long userId){
        try {
            return ResponseEntity.ok(favoriteService.getByUserId(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FavoriteDto> add(@RequestBody FavoriteAddProductRequest request){
        return ResponseEntity.ok(favoriteService.add(request));
    }

    @PutMapping
    public ResponseEntity<FavoriteDto> update(@RequestBody FavoriteAddProductRequest request){
        return ResponseEntity.ok(favoriteService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            favoriteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
