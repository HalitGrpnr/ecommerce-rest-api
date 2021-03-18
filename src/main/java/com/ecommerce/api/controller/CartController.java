package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.request.CartAddProductRequest;
import com.ecommerce.api.service.CartService;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getByUserId(@PathVariable("userId") Long userId){
        try {
            return ResponseEntity.ok(cartService.getByUserId(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartDto> add(@RequestBody CartAddProductRequest request){
        return ResponseEntity.ok(cartService.add(request));
    }

    @PutMapping
    public ResponseEntity<CartDto> update(@RequestBody CartAddProductRequest request){
        return ResponseEntity.ok(cartService.update(request));
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
