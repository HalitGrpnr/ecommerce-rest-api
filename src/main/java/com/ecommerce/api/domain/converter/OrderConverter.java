package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.OrderDto;
import com.ecommerce.api.domain.entity.Order;

public class OrderConverter implements BaseConverter<OrderDto, Order> {
    @Override
    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setCreatedDate(order.getCreatedDate());
        orderDto.setTotalPrice(order.getTotalPrice());

        return orderDto;
    }

    @Override
    public Order convertToEntity(OrderDto orderDto) {
        Order order= new Order();

        order.setCreatedDate(orderDto.getCreatedDate());
        order.setTotalPrice(orderDto.getTotalPrice());

        return order;
    }
}
