package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.ProductConverter;
import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    
    private ProductRepository productRepository;
    private ProductConverter productConverter = new ProductConverter();

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto get(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        ProductDto productDto = productConverter.convertToDto(product);
        return productDto;
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = productConverter.convertToEntity(productDto);
        product = productRepository.save(product);

        return productConverter.convertToDto(product);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(c -> productConverter.convertToDto(c)).collect(Collectors.toList());
        return productDtos;
    }
}
