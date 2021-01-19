package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CartConverter;
import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import com.ecommerce.api.repository.CartRepository;
import com.ecommerce.api.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartConverter cartConverter;

    public CartServiceImpl(CartRepository cartRepository, CartConverter cartConverter) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
    }

    @Override
    public CartDto get(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return cartConverter.convert(cart);
    }

    @Override
    public CartDto add(CartDto cartDto) {
        Cart cart = cartConverter.convert(cartDto);
        cart = cartRepository.save(cart);

        return cartConverter.convert(cart);
    }

    @Override
    public CartDto update(CartDto cartDto) {
        Cart cart = cartRepository.save(cartConverter.convert(cartDto));
        return cartConverter.convert(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.findAll();

        if (CollectionUtils.isEmpty(carts)) {
            throw new EntityNotFoundException();
        }

        return carts.stream().map(cartConverter::convert).collect(Collectors.toList());
    }
}
