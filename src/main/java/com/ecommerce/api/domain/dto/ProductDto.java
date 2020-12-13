package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProductDto implements Serializable {

    private static final long serialVersionUID = -6911283630438089730L;
    private Long id;
    private String name;
    private String description;
    private double price;
    private Date createdDate;
    private Date LastModifiedDate;
    private int leadTime;
    private double averageRate;

    @JsonBackReference("store-product")
    private StoreDto storeDto;

    @JsonManagedReference("product-comment")
    private List<CommentDto> comments;

    @JsonManagedReference("product-rating")
    private List<RatingDto> ratings;

    @JsonIgnore
    private List<CartDto> carts;

    private List<CategoryDto> categories;

    @JsonIgnore
    private List<OrderDto> orders;
}
