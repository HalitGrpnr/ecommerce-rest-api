package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.request.ProductAddRequest;
import com.ecommerce.api.domain.request.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    ProductDto get(Long id);
    ProductDto add(ProductAddRequest request);
    ProductDto update(ProductUpdateRequest request);
    void delete(Long id);
    List<ProductDto> getAll();
}
