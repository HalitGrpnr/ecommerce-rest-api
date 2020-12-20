package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.get(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.add(productDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

}
