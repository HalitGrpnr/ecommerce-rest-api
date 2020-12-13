package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RatingDto implements Serializable {
    private static final long serialVersionUID = 5530357033798932308L;

    private Long id;
    private int rate;
    private Date createdDate;

    @JsonManagedReference("rating-user")
    private UserDto userDto;

    @JsonManagedReference("rating-store")
    private StoreDto storeDto;

    @JsonManagedReference("rating-product")
    private ProductDto productDto;
}
