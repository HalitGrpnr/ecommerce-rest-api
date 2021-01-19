package com.ecommerce.api.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductAddRequest extends BaseRequest{
    private static final long serialVersionUID = 1835342161611339631L;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer leadTime;

    @NotEmpty
    private List<Long> categories;
}
