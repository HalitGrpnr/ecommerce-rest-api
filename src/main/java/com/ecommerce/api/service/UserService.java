package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.UserDto;
import com.ecommerce.api.domain.entity.User;
import com.ecommerce.api.domain.request.UserAddRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto get(Long id);
    User find(Long id);
    UserDto add(UserAddRequest request);
    UserDto update(UserDto userDto);
    void delete(Long id);
    List<UserDto> getAll();
}
