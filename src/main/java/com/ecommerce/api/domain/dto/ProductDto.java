package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductDto extends BaseDto {

    private static final long serialVersionUID = -6911283630438089730L;
    private Long id;
    private String name;
    private String description;
    private double price;
    private Date createdDate;
    private Date LastModifiedDate;
    private int leadTime;
    private double averageRate;
    private boolean liked;

    @JsonBackReference("store-product")
    private StoreDto storeDto;

    @JsonManagedReference("product-comment")
    private List<CommentDto> comments;

    @JsonManagedReference("product-rating")
    private List<RatingDto> ratings;

    private List<AvatarDto> avatars;
    private List<CategoryDto> categories;

}
