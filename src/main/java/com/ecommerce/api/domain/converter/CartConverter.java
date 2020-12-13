package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;

public class CartConverter implements BaseConverter<CartDto, Cart>{

    @Override
    public CartDto convertToDto (Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());

        return cartDto;
    }

    @Override
    public Cart convertToEntity (CartDto cartDto) {
        Cart cart = new Cart();
        return cart;
    }
}
