package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto get(Long id);
    UserDto add(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Long id);
    List<UserDto> getAll();
}
