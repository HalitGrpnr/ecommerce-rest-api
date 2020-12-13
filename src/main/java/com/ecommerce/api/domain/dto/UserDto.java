package com.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 8786429431608705281L;
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private Date createdDate;
    private String address;
    private String userRole;
    private Boolean locked = false;
    private Boolean enabled = true;

    @JsonManagedReference("user-cart")
    private CartDto cartDto;

    @JsonManagedReference("user-store")
    private StoreDto storeDto;

    @JsonBackReference("comment-user")
    private List<CommentDto> comments;

    @JsonBackReference("rating-user")
    private List<RatingDto> ratings;

    @JsonBackReference("order-user")
    private List<OrderDto> orders;
}
