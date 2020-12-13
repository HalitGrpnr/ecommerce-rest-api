package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto get(Long id);
    ProductDto add(ProductDto productDto);
    ProductDto update(ProductDto productDto);
    void delete(Long id);
    List<ProductDto> getAll();
}
