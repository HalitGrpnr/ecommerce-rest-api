package com.ecommerce.api.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductUpdateRequest extends BaseRequest{
    private static final long serialVersionUID = 2578051372706460901L;

    @NotNull
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer leadTime;
    private List<Long> categories;
}
