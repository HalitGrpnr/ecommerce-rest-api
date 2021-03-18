package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.CartConverter;
import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Cart;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.domain.entity.User;
import com.ecommerce.api.domain.request.CartAddProductRequest;
import com.ecommerce.api.repository.CartRepository;
import com.ecommerce.api.service.CartService;
import com.ecommerce.api.service.ProductService;
import com.ecommerce.api.service.UserService;
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
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, CartConverter cartConverter, ProductService productService, UserService userService) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public CartDto get(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return cartConverter.convert(cart);
    }

    @Override
    public CartDto add(CartAddProductRequest request) {
        User user = userService.find(request.getUserId());
        List<Product> products = productService.getByIds(request.getProductIds());

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(products);
        cart = save(cart);

        return cartConverter.convert(cart);
    }

    public Cart save(Cart cart) {
        cart = cartRepository.save(cart);
        return cart;
    }

    @Override
    public CartDto update(CartAddProductRequest request) {
        Cart cart = cartRepository.findByUser_Id(request.getUserId());
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

    @Override
    public CartDto getByUserId(Long userId) {
        Cart cart = cartRepository.findByUser_Id(userId);
        return cartConverter.convert(cart);
    }
}
