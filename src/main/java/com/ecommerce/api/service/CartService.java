package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.CartDto;

import java.util.List;

public interface CartService {
    CartDto get(Long id);
    CartDto add(CartDto cartDto);
    CartDto update(CartDto cartDto);
    void delete(Long id);
    List<CartDto> getAll();
}
