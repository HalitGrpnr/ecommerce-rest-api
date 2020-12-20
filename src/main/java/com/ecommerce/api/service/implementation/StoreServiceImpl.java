package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.StoreConverter;
import com.ecommerce.api.domain.dto.StoreDto;
import com.ecommerce.api.domain.entity.Store;
import com.ecommerce.api.repository.StoreRepository;
import com.ecommerce.api.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    
    private StoreRepository storeRepository;
    private StoreConverter storeConverter;

    public StoreServiceImpl(StoreRepository storeRepository, StoreConverter storeConverter) {
        this.storeRepository = storeRepository;
        this.storeConverter = storeConverter;
    }

    @Override
    public StoreDto get(Long id) {
        Store store = storeRepository.findById(id).orElse(new Store());
        StoreDto storeDto = storeConverter.convertToDto(store);
        return storeDto;
    }

    @Override
    public StoreDto add(StoreDto storeDto) {
        Store store = storeConverter.convertToEntity(storeDto);
        store = storeRepository.save(store);

        return storeConverter.convertToDto(store);
    }

    @Override
    public StoreDto update(StoreDto storeDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public List<StoreDto> getAll() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDto> storeDtos = stores.stream().map(c -> storeConverter.convertToDto(c)).collect(Collectors.toList());
        return storeDtos;
    }
}
