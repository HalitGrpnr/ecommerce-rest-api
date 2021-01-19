package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RatingDto extends BaseDto {
    private static final long serialVersionUID = 5530357033798932308L;

    private Long id;
    private int rate;
    private Date createdDate;

    @JsonBackReference("user-rating")
    private UserDto userDto;

    @JsonBackReference("store-rating")
    private StoreDto storeDto;

    @JsonBackReference("product-rating")
    private ProductDto productDto;
}
