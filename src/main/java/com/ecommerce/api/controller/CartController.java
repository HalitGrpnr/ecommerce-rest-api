package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAll(){
        return ResponseEntity.ok(cartService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable("id") Long id){
        return ResponseEntity.ok(cartService.get(id));
    }

    @PostMapping
    public ResponseEntity<CartDto> addCart(@RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.add(cartDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id){
        cartService.delete(id);
    }

}
