package com.ecommerce.api.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAddRequest extends BaseRequest {

    private static final long serialVersionUID = -3545303359158632011L;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    private String phone;
    private String address;
}
