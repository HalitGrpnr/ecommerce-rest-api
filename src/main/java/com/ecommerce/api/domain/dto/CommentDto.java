package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDto implements Serializable {
    private static final long serialVersionUID = 7596538674447746524L;

    private Long id;
    private String content;
    private Date createdDate;

    @JsonManagedReference("comment-user")
    private UserDto userDto;

    @JsonManagedReference("comment-store")
    private StoreDto storeDto;

    @JsonManagedReference("comment-product")
    private ProductDto productDto;
}
