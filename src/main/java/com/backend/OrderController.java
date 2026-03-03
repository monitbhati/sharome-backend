package com.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    // --- EXISTING ENDPOINTS ---
    @PostMapping("/submit")
    public OrderTicket submitOrder(@RequestBody OrderTicket order) {
        return orderRepository.save(order);
    }
    @GetMapping("/all")
    public List<OrderTicket> getAllOrders() {
        return orderRepository.findAll();
    }
    // --- NEW ENDPOINTS FOR 14-DAY TRACKING ---
    // 1. Customer Tracking Endpoint
    @GetMapping("/track/{phoneNumber}")
    public ResponseEntity<OrderTicket> trackOrder(@PathVariable String phoneNumber) {
        Optional<OrderTicket> order = orderRepository.findByPhoneNumber(phoneNumber);
        
        return order.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // 2. Admin Override Endpoint
    @PatchMapping("/status/{orderId}")
    public ResponseEntity<OrderTicket> updateOrderStatus(
            @PathVariable Long orderId, 
            @RequestBody Map<String, String> requestBody) {
        
        Optional<OrderTicket> existingOrder = orderRepository.findById(orderId);
        
        if (existingOrder.isPresent()) {
            OrderTicket order = existingOrder.get();
            
            if (requestBody.containsKey("status")) {
                order.setStatus(requestBody.get("status")); 
            }
            
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }
        
        return ResponseEntity.notFound().build();
    }
}