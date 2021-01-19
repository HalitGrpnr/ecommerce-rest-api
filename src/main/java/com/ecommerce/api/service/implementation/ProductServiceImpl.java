package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.ProductConverter;
import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.entity.Category;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.domain.request.ProductAddRequest;
import com.ecommerce.api.domain.request.ProductUpdateRequest;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductConverter productConverter,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.categoryService = categoryService;
    }


    @Override
    public ProductDto get(Long id) {
        return productConverter.convert(findById(id));
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductDto add(ProductAddRequest request) {
        List<Category> categories = categoryService.findByIds(request.getCategories());

        Product product = productConverter.convert(request);
        product.setCategories(categories);

        return productConverter.convert(productRepository.save(product));
    }

    @Override
    public ProductDto update(ProductUpdateRequest request) {
        List<Category> categories = categoryService.findByIds(request.getCategories());
        Product product = findById(request.getId());
        productConverter.convert(request, product);
        product.setCategories(categories);
        return productConverter.convert(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();

        if (CollectionUtils.isEmpty(products)) {
            throw new EntityNotFoundException();
        }

        return products.stream().map(productConverter::convert).collect(Collectors.toList());
    }
}
