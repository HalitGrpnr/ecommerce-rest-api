package com.ecommerce.api.domain.converter;

import com.ecommerce.api.domain.dto.OrderDto;
import com.ecommerce.api.domain.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderConverter {
    public OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setCreatedDate(order.getCreatedDate());
        orderDto.setTotalPrice(order.getTotalPrice());

        return orderDto;
    }

    public Order convert(OrderDto orderDto) {
        Order order= new Order();

        order.setId(orderDto.getId());
        order.setCreatedDate(orderDto.getCreatedDate());
        order.setTotalPrice(orderDto.getTotalPrice());

        return order;
    }
}
