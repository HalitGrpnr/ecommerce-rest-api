package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CategoryDto extends BaseDto {
    private static final long serialVersionUID = 9019248235104422013L;

    private Long id;
    private Long parentId;
    private String name;
    private Date createdDate;

    @JsonIgnore
    private List<ProductDto> products;
}
