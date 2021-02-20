package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.AvatarDto;
import com.ecommerce.api.domain.dto.CartDto;
import com.ecommerce.api.domain.entity.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AvatarService {
    AvatarDto get(Long id);
    AvatarDto add(AvatarDto avatarDto);
    Avatar save(MultipartFile image);
    List<Avatar> save(List<MultipartFile> image);
    AvatarDto update(AvatarDto avatarDto);
    void delete(Long id);
}
