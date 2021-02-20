package com.ecommerce.api.domain.dto;

import lombok.Data;

@Data
public class AvatarDto extends BaseDto {

    private static final long serialVersionUID = 7956502708792008117L;

    private Long id;
    private byte[] image;
}
