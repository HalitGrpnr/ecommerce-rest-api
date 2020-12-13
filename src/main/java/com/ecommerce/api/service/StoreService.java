package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.StoreDto;

import java.util.List;

public interface StoreService {
    StoreDto get(Long id);
    StoreDto add(StoreDto storeDto);
    StoreDto update(StoreDto storeDto);
    void delete(Long id);
    List<StoreDto> getAll();
}
