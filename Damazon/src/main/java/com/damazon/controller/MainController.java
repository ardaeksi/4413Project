package com.damazon.controller;

import com.damazon.model.Order;
import com.damazon.model.Item;
import com.damazon.model.User;
import com.damazon.service.OrderService;
import com.damazon.service.ItemService;
import com.damazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.damazon.dto.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UserService userService;
    private final ItemService productService;
    private final OrderService orderService;

    @Autowired
    public MainController(UserService userService, ItemService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // User endpoints
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.authenticateUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.registerUser(user);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    // Product endpoints
    @GetMapping("/products")
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Order endpoints
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        if (orderService.deleteOrder(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound
