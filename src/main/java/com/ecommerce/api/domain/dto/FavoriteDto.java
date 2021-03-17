package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.List;

@Data
public class FavoriteDto extends BaseDto {

    private static final long serialVersionUID = -1560996118363540202L;
    private Long id;

    @JsonBackReference("user-favorite")
    private UserDto userDto;

    private List<ProductDto> products;
}
