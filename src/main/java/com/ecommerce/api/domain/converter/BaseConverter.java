package com.ecommerce.api.domain.converter;

public interface BaseConverter<A, B> {
    A convertToDto (B entity);
    B convertToEntity (A dto);
}