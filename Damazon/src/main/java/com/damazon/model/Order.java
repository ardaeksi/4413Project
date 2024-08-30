package com.damazon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long orderId;

    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "review")
    private String review;

    public Long getOrderId() {
        return orderId;
    }

    public void setId(Long id) {
        this.orderId = id;
    }

    public Long getUserID() {
        return userId;
    }

    public void setUserID(Long userIde) {
        this.userId = userIde;
    }

    public Double getCost() {
        return cost;
    }
    
    public void setCost(Double cost) {
    	this.cost = cost;
    }
    
    public String getReview() {
    	return this.review;
    }
    
    public void setReview(String review) {
    	this.review = review;
    }
    
}
