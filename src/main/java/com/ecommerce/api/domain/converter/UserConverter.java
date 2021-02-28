package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.UserDto;
import com.ecommerce.api.domain.entity.User;
import com.ecommerce.api.domain.request.UserAddRequest;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserDto convert(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setEnabled(user.getEnabled());
        userDto.setLocked(user.getLocked());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        userDto.setSurname(user.getSurname());
        userDto.setUserRole(user.getUserRole());

        return userDto;
    }

    public User convert(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setEnabled(userDto.getEnabled());
        user.setLocked(userDto.getLocked());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setSurname(userDto.getSurname());
        user.setUserRole(userDto.getUserRole());

        return user;
    }

    public User convert(UserAddRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        return user;
    }
}