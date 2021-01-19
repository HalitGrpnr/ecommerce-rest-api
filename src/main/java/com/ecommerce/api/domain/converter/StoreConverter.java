package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.StoreDto;
import com.ecommerce.api.domain.entity.Store;
import org.springframework.stereotype.Service;

@Service
public class StoreConverter {
    public StoreDto convert(Store store) {
        StoreDto storeDto = new StoreDto();

        storeDto.setId(store.getId());
        storeDto.setName(store.getName());
        storeDto.setAverageRate(store.getAverageRate());

        return storeDto;
    }

    public Store convert(StoreDto storeDto) {
        Store store = new Store();

        store.setId(storeDto.getId());
        store.setName(storeDto.getName());
        store.setAverageRate(storeDto.getAverageRate());

        return store;
    }
}