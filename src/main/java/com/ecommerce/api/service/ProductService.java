package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.request.ProductAddRequest;
import com.ecommerce.api.domain.request.ProductUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductDto get(Long id);
    List<ProductDto> getByCategoryId(Long categoryId);
    ProductDto add(ProductAddRequest request, List<MultipartFile> images);
    ProductDto update(ProductUpdateRequest request);
    void delete(Long id);
    List<ProductDto> getAll();
}
