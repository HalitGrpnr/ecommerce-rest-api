package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.service.CartService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAll(){
        try {
            return ResponseEntity.ok(cartService.getAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(cartService.get(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartDto> add(@RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.add(cartDto));
    }

    @PutMapping
    public ResponseEntity<CartDto> update(@RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.update(cartDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            cartService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
