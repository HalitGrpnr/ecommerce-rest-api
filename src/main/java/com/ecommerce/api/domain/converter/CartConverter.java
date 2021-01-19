package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartConverter {

    public CartDto convert(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());

        return cartDto;
    }

    public Cart convert(CartDto cartDto) {
        Cart cart = new Cart();

        cart.setId(cartDto.getId());

        return cart;
    }
}
