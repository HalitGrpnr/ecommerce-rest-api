package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartDto extends BaseDto {

    private static final long serialVersionUID = -4852773815810689694L;
    private Long id;

    @JsonBackReference("user-cart")
    private UserDto userDto;

    private List<ProductDto> products;
}
