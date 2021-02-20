package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.domain.request.ProductAddRequest;
import com.ecommerce.api.domain.request.ProductUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductConverter {

    private CategoryConverter categoryConverter;
    private AvatarConverter avatarConverter;

    public ProductConverter(CategoryConverter categoryConverter, AvatarConverter avatarConverter) {
        this.categoryConverter = categoryConverter;
        this.avatarConverter = avatarConverter;
    }

    public ProductDto convert(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setLeadTime(product.getLeadTime());
        productDto.setDescription(product.getDescription());
        productDto.setAverageRate(product.getAverageRate());
        productDto.setCategories(categoryConverter.convert(product.getCategories()));
        productDto.setAvatars(avatarConverter.convert(product.getAvatars()));

        return productDto;
    }

    public Product convert(ProductDto productDto){
        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setLeadTime(productDto.getLeadTime());
        product.setDescription(productDto.getDescription());
        product.setAverageRate(productDto.getAverageRate());

        return product;
    }

    public Product convert(ProductAddRequest request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setLeadTime(request.getLeadTime());
        product.setDescription(request.getDescription());

        return product;
    }

    public void convert(ProductUpdateRequest request, Product product) {
        product.setId(request.getId());

        if (StringUtils.hasLength(request.getDescription())) {
            product.setDescription(request.getDescription());

        }
        if (StringUtils.hasLength(request.getName())) {
            product.setName(request.getName());

        }
        if (request.getPrice() != null && request.getPrice() > 0.0) {
            product.setPrice(request.getPrice());

        }
        if (request.getLeadTime() != null && request.getLeadTime() > 0) {
            product.setLeadTime(request.getLeadTime());

        }
    }
}