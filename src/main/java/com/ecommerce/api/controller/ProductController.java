package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.request.ProductAddRequest;
import com.ecommerce.api.domain.request.ProductUpdateRequest;
import com.ecommerce.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        try {
            return ResponseEntity.ok(productService.getAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(productService.get(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> add(@RequestBody ProductAddRequest request){
        return ResponseEntity.ok(productService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductUpdateRequest  request){
        return ResponseEntity.ok(productService.update(request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

}
