package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class StoreDto extends BaseDto {

    private static final long serialVersionUID = 324836443313379550L;
    private Long id;
    private String name;
    private Date createdDate;
    private double averageRate;

    @JsonManagedReference("store-comment")
    private List<CommentDto> comments;

    @JsonManagedReference("store-rating")
    private List<RatingDto> ratings;

    @JsonManagedReference("store-product")
    private List<ProductDto> products;

    @JsonBackReference("user-store")
    private UserDto userDto;
}
