package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CartConverter;
import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.domain.request.CartAddProductRequest;
import com.ecommerce.api.repository.CartRepository;
import com.ecommerce.api.service.CartService;
import com.ecommerce.api.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartConverter cartConverter;
    private final ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, CartConverter cartConverter, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
        this.productService = productService;
    }

    @Override
    public CartDto get(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return cartConverter.convert(cart);
    }

    @Override
    public CartDto add(CartDto cartDto) {
        Cart cart = cartConverter.convert(cartDto);
        cart = save(cart);

        return cartConverter.convert(cart);
    }

    public Cart save(Cart cart) {
        cart = cartRepository.save(cart);
        return cart;
    }

    @Override
    public CartDto update(CartAddProductRequest request) {
        Cart cart = cartRepository.findById(request.getCartId()).orElseThrow(EntityNotFoundException::new);
        List<Product> products = productService.getByIds(request.getProductIds());
        cart.setProducts(products);
        cart = cartRepository.save(cart);
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
