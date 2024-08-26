package com.damazon.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "orderID")
    private Long orderId;

    @Column(name = "userID", nullable = false)
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
