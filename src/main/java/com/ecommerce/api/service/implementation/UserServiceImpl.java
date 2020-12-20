package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.UserConverter;
import com.ecommerce.api.domain.dto.UserDto;
import com.ecommerce.api.domain.entity.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.UserService;
import org.springframework.stereotype.Service;

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
        UserDto userDto = userConverter.convertToDto(user);
        return userDto;
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = userConverter.convertToEntity(userDto);
        user = userRepository.save(user);

        return userConverter.convertToDto(user);
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
        List<UserDto> userDtos = users.stream().map(c -> userConverter.convertToDto(c)).collect(Collectors.toList());
        return userDtos;
    }
}
