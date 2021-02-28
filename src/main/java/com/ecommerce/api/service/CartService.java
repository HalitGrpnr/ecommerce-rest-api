package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import com.ecommerce.api.domain.request.CartAddProductRequest;

import java.util.List;

public interface CartService {
    CartDto get(Long id);
    CartDto add(CartDto cartDto);
    Cart save(Cart cart);
    CartDto update(CartAddProductRequest request);
    void delete(Long id);
    List<CartDto> getAll();
}
