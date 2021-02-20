package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.AvatarDto;
import com.ecommerce.api.domain.entity.Avatar;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvatarConverter {

    public AvatarDto convert(Avatar avatar) {
        AvatarDto avatarDto = new AvatarDto();

        avatarDto.setId(avatar.getId());
        avatarDto.setImage(avatar.getImage());

        return avatarDto;
    }

    public Avatar convert(AvatarDto avatarDto) {
        Avatar avatar = new Avatar();

        avatar.setId(avatarDto.getId());
        avatar.setImage(avatarDto.getImage());

        return avatar;
    }

    public List<AvatarDto> convert(List<Avatar> avatars) {
        if (CollectionUtils.isEmpty(avatars)) {
            return Collections.emptyList();
        }
        return avatars.stream().map(this::convert).collect(Collectors.toList());
    }
}
