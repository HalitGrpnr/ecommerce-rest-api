package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto implements Serializable {
    private static final long serialVersionUID = -9065486762088964837L;

    private Long id;
    private double totalPrice;
    private Date createdDate;

    @JsonManagedReference("order-user")
    private UserDto userDto;

    private List<ProductDto> products;
}
