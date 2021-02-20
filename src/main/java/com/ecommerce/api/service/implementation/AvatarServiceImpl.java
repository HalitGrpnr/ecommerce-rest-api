package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.AvatarConverter;
import com.ecommerce.api.domain.dto.AvatarDto;
import com.ecommerce.api.domain.entity.Avatar;
import com.ecommerce.api.repository.AvatarRepository;
import com.ecommerce.api.service.AvatarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final AvatarConverter avatarConverter;

    public AvatarServiceImpl(AvatarRepository avatarRepository, AvatarConverter avatarConverter) {
        this.avatarRepository = avatarRepository;
        this.avatarConverter = avatarConverter;
    }

    @Override
    public AvatarDto get(Long id) {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return avatarConverter.convert(avatar);
    }

    @Override
    public AvatarDto add(AvatarDto avatarDto) {
        Avatar avatar = avatarConverter.convert(avatarDto);
        avatar = avatarRepository.save(avatar);

        return avatarConverter.convert(avatar);
    }

    @Override
    public Avatar save(MultipartFile image) {
        Avatar avatar = new Avatar();
        try {
            avatar.setImage(image.getBytes());
        } catch (IOException e) {
            //TODO
        }
        return avatarRepository.save(avatar);
    }

    @Override
    public List<Avatar> save(List<MultipartFile> images) {
        List<Avatar> list = new ArrayList<>();
            images.forEach(image -> {
                Avatar avatar = new Avatar();
                try {
                    avatar.setImage(image.getBytes());
                    list.add(avatarRepository.save(avatar));
                } catch (IOException e) {
                    // TODO
                }
            });

        return list;
    }

    @Override
    public AvatarDto update(AvatarDto avatarDto) {
        Avatar avatar = avatarRepository.save(avatarConverter.convert(avatarDto));
        return avatarConverter.convert(avatar);
    }

    @Override
    public void delete(Long id) {
        avatarRepository.deleteById(id);
    }
}
