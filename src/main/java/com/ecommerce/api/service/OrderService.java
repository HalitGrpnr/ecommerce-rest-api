package com.ecommerce.api.service;

import com.ecommerce.api.domain.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto get(Long id);
    OrderDto add(OrderDto orderDto);
    OrderDto update(OrderDto orderDto);
    void delete(Long id);
    List<OrderDto> getAll();
}
