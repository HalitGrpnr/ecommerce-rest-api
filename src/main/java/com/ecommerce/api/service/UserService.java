package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.UserDto;
import com.ecommerce.api.domain.request.UserAddRequest;

import java.util.List;

public interface UserService {
    UserDto get(Long id);
    UserDto add(UserAddRequest request);
    UserDto update(UserDto userDto);
    void delete(Long id);
    List<UserDto> getAll();
}
