package com.ecommerce.api.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto extends BaseDto {
    private static final long serialVersionUID = 9019248235104422013L;

    private Long id;
    private Long parentId;
    private String name;
    private Date createdDate;

}
