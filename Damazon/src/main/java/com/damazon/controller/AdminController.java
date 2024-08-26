package com.damazon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.damazon.dto.*;
import com.damazon.model.*;
import com.damazon.service.*;


@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") //Admin can only reach with class
public class AdminController {


    private final UserService userService;
    private final ItemService productService;
    private final OrderService orderService;
    
    @Autowired
    public AdminController(UserService userService, ItemService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }
    // Products Management
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
    
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Item product) {
        Item savedProduct = productService.saveProduct(product);
        if (savedProduct != null) { 
        	if (savedProduct.getItemId() != null)
            return ResponseEntity.status(201).build();  // Only return 201 Created status
        }
            return ResponseEntity.badRequest().build();  // In case the save operation fails
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Item> updateProduct(@PathVariable Integer id, @RequestBody Item product) {
        Item updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        boolean wasDeleted = productService.deleteProduct(id);
        if (wasDeleted) {
            return ResponseEntity.ok().build();  // 200 when deleted
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found if the product was not found
        }
    }
    
    //Returns the product but also the amount of product
    @GetMapping("/products/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName) {
        ItemResponse itemResponse = productService.findItemByName(productName);
        if (itemResponse != null) {
            return ResponseEntity.ok(itemResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Users Management operationss
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // Orders Management
    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getSalesHistory());
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    // Sales History Management
    @GetMapping("/sales")
    public ResponseEntity<?> getSalesHistory() {
        return ResponseEntity.ok(orderService.getSalesHistory());
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<?> updateSalesRecord(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return ResponseEntity.ok(orderService.updateOrder(id, updatedOrder));
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<?> deleteSalesRecord(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
