package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.UserConverter;
import com.ecommerce.api.domain.dto.UserDto;
import com.ecommerce.api.domain.entity.Cart;
import com.ecommerce.api.domain.entity.Favorite;
import com.ecommerce.api.domain.entity.User;
import com.ecommerce.api.domain.request.UserAddRequest;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto get(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        UserDto userDto = userConverter.convert(user);
        return userDto;
    }

    @Override
    public User find(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserDto add(UserAddRequest request) {
        User user = userConverter.convert(request);
        addCart(user);
        addFavorite(user);
        user = userRepository.save(user);

        return userConverter.convert(user);
    }

    private void addFavorite(User user) {
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        user.setFavorite(favorite);
    }

    private void addCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(c -> userConverter.convert(c)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
}
