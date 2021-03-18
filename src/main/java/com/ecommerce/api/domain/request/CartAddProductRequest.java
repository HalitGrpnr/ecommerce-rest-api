package com.ecommerce.api.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CartAddProductRequest extends BaseRequest {
    private static final long serialVersionUID = 3506324541957613714L;

    @NotNull
    private Long userId;

    @NotEmpty
    private List<Long> productIds;
}
