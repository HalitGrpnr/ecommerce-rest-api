package com.ecommerce.api.controller;

import com.ecommerce.api.domain.dto.OrderDto;
import com.ecommerce.api.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.get(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.add(orderDto));
    }

    //put

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.delete(id);
    }

}
