package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.ProductConverter;
import com.ecommerce.api.domain.dto.ProductDto;
import com.ecommerce.api.domain.entity.Avatar;
import com.ecommerce.api.domain.entity.Category;
import com.ecommerce.api.domain.entity.Product;
import com.ecommerce.api.domain.request.ProductAddRequest;
import com.ecommerce.api.domain.request.ProductUpdateRequest;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.service.AvatarService;
import com.ecommerce.api.service.CategoryService;
import com.ecommerce.api.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;
    private final AvatarService avatarService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductConverter productConverter,
                              CategoryService categoryService, AvatarService avatarService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.categoryService = categoryService;
        this.avatarService = avatarService;
    }


    @Override
    public ProductDto get(Long id) {
        return productConverter.convert(findById(id));
    }

    @Override
    public List<Product> getByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    @Override
    public List<ProductDto> getByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategories_Id(categoryId);
        return productConverter.convert(products);
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductDto add(ProductAddRequest request, List<MultipartFile> images) {
        Product product = productConverter.convert(request);

        //TODO add validators
        List<Category> categories = categoryService.findByIds(request.getCategories());
        product.setCategories(categories);

        if (!CollectionUtils.isEmpty(images)) {
            List<Avatar> avatars = avatarService.save(images);

            avatars.forEach(avatar -> avatar.setProduct(product));

            product.setAvatars(avatars);
        }

        return productConverter.convert(productRepository.save(product));
    }

    @Override
    public ProductDto update(ProductUpdateRequest request) {
        Product product = findById(request.getId());
        productConverter.convert(request, product);
        if (request.getCategories() != null) {
            List<Category> categories = categoryService.findByIds(request.getCategories());
            product.setCategories(categories);

        }
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

        return productConverter.convert(products);
    }

    @Override
    public List<ProductDto> getHomePageData() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> response = productRepository.findAll(pageable);

        if (CollectionUtils.isEmpty(response.getContent())) {
            throw new EntityNotFoundException();
        }

        return productConverter.convert(response.getContent());
    }
}
