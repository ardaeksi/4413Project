package com.damazon.repository;

import com.damazon.model.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    //Uses JPA language rather than SQL query since it is a service offered
	@Query("SELECT o FROM Order o WHERE o.userId = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);
    
	
    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId")
    Optional <Order> findByOrderId(@Param("orderId")Long orderId);
    
    @Query("SELECT COUNT(o) > 0 FROM Order o WHERE o.orderId = :orderId")
    boolean existsById(@Param("orderId") Long orderId);
    
    
	
	
    
    
}
