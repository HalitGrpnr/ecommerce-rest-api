package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartConverter {

    private ProductConverter productConverter;

    public CartConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CartDto convert(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        cartDto.setProducts(productConverter.convert(cart.getProducts()));

        return cartDto;
    }

    public Cart convert(CartDto cartDto) {
        Cart cart = new Cart();

        cart.setId(cartDto.getId());

        return cart;
    }
}
