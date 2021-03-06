package com.ecommerce.api.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HomePageDto extends BaseDto {
    private static final long serialVersionUID = 1326402894330233460L;

    private List<CategoryDto> categories;
    private List<ProductDto> products;
}
