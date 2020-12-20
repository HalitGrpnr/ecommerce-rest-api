package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.StoreDto;
import com.ecommerce.api.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/stores")
public class StoreController {

    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll(){
        return ResponseEntity.ok(storeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStore(@PathVariable("id") Long id){
        return ResponseEntity.ok(storeService.get(id));
    }

    @PostMapping
    public ResponseEntity<StoreDto> addStore(@RequestBody StoreDto storeDto){
        return ResponseEntity.ok(storeService.add(storeDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id){
        storeService.delete(id);
    }

}
