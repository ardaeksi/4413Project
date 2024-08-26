package com.damazon.controller;

import com.damazon.model.Order;
import com.damazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get order by ID, specific to a user
    @GetMapping("/getUserOrder/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all orders for a user
    @GetMapping("/getAllUserOrders")
    public ResponseEntity<List<Order>> getAllOrdersForUser(@RequestParam Long userId) {
        List<Order> orders = orderService.getAllOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    // Create a new order
    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }


    // Delete an order
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
