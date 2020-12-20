package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CartConverter;
import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import com.ecommerce.api.repository.CartRepository;
import com.ecommerce.api.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private CartConverter cartConverter;

    public CartServiceImpl(CartRepository cartRepository, CartConverter cartConverter) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
    }

    @Override
    public CartDto get(Long id) {
        Cart cart = cartRepository.findById(id).orElse(new Cart());
        CartDto cartDto = cartConverter.convertToDto(cart);
        return cartDto;
    }

    @Override
    public CartDto add(CartDto cartDto) {
        Cart cart = cartConverter.convertToEntity(cartDto);
        cart = cartRepository.save(cart);

        return cartConverter.convertToDto(cart);
    }

    @Override
    public CartDto update(CartDto cartDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDtos = carts.stream().map(c -> cartConverter.convertToDto(c)).collect(Collectors.toList());
        return cartDtos;
    }
}
