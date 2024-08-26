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
    
    
    // Fetch all orders (only for admin)
    public List<Order> getSalesHistory() {
        return orderRepository.findAll();
    }
	
    // Fetch a single order by orderID
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }
    // Fetch all orders by userID
    public List <Order> getAllOrdersByUserId(Long userId){
    	return orderRepository.findByUserId(userId);
    }
    // Create a new order
    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    
    
    // Update an existing order
    @Transactional
    public Order updateOrder(Long orderId, Order updatedOrder) {
        return orderRepository.findById(orderId)
            .map(existingOrder -> {
                // Updates the fields of the existing order, since only these 2 fields made sense to change included these
                existingOrder.setReview(updatedOrder.getReview());
                return orderRepository.save(existingOrder);
            })
            .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }
	
    // Delete an order
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

}
