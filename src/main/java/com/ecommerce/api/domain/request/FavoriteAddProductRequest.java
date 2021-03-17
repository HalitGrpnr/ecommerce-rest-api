package com.ecommerce.api.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FavoriteAddProductRequest extends BaseRequest {

    private static final long serialVersionUID = -859976633762327473L;
    @NotNull
    private Long userId;

    @NotEmpty
    private List<Long> productIds;
}
