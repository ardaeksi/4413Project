package com.damazon.service;

import com.damazon.model.Order;
import com.damazon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Fetch all orders
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // Fetch a single order by ID
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Create a new order
    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Update an existing order
    @Transactional
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
            .map(order -> {
                order.setOrderStatus(updatedOrder.getOrderStatus());
                order.setOrderDetails(updatedOrder.getOrderDetails());
                // Add more fields as necessary
                return orderRepository.save(order);
            })
            .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    // Delete an order
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Additional methods for business logic related to orders can be added here
}
