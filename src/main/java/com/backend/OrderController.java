package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/submit")
    public OrderTicket submitOrder(@RequestBody OrderTicket order) {
        return orderRepository.save(order);
    }

    @GetMapping("/all")
    public List<OrderTicket> getAllOrders() {
        return orderRepository.findAll();
    }
}