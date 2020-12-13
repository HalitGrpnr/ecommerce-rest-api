package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.StoreDto;
import com.ecommerce.api.domain.entity.Store;

public class StoreConverter implements BaseConverter<StoreDto, Store>{
    @Override
    public StoreDto convertToDto(Store store) {
        StoreDto storeDto = new StoreDto();

        storeDto.setId(store.getId());
        storeDto.setName(store.getName());
        storeDto.setAverageRate(store.getAverageRate());

        return storeDto;
    }

    @Override
    public Store convertToEntity(StoreDto storeDto) {
        Store store = new Store();

        store.setName(storeDto.getName());
        store.setAverageRate(storeDto.getAverageRate());

        return store;
    }
}