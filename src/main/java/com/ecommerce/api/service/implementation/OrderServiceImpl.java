package com.ecommerce.api.service.implementation;

import com.ecommerce.api.domain.converter.OrderConverter;
import com.ecommerce.api.domain.dto.OrderDto;
import com.ecommerce.api.domain.entity.Order;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    
    private OrderRepository orderRepository;
    private OrderConverter orderConverter = new OrderConverter();

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto get(Long id) {
        Order order = orderRepository.findById(id).orElse(new Order());
        OrderDto orderDto = orderConverter.convertToDto(order);
        return orderDto;
    }

    @Override
    public OrderDto add(OrderDto orderDto) {
        Order order = orderConverter.convertToEntity(orderDto);
        order = orderRepository.save(order);

        return orderConverter.convertToDto(order);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream().map(c -> orderConverter.convertToDto(c)).collect(Collectors.toList());
        return orderDtos;
    }
}
