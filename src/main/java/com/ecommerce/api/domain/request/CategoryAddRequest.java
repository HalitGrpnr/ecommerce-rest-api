package com.ecommerce.api.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryAddRequest extends BaseRequest {
    private static final long serialVersionUID = -8870372406368844810L;

    private Long parentId;

    @NotEmpty
    private String name;
}
