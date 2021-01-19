package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDto extends BaseDto {
    private static final long serialVersionUID = 7596538674447746524L;

    private Long id;
    private String content;
    private Date createdDate;

    @JsonBackReference("user-comment")
    private UserDto userDto;

    @JsonBackReference("store-comment")
    private StoreDto storeDto;

    @JsonBackReference("product-comment")
    private ProductDto productDto;
}
