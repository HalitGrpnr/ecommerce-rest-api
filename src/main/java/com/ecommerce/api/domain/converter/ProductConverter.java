package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.entity.Product;

public class ProductConverter implements BaseConverter<ProductDto, Product> {

    @Override
    public ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setLeadTime(product.getLeadTime());
        productDto.setDescription(product.getDescription());
        productDto.setAverageRate(product.getAverageRate());

        return productDto;
    }

    @Override
    public Product convertToEntity(ProductDto productDto){
        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setLeadTime(productDto.getLeadTime());
        product.setDescription(productDto.getDescription());
        product.setAverageRate(productDto.getAverageRate());

        return product;
    }
}