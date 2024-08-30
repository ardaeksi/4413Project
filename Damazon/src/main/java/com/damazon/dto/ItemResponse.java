package com.damazon.dto;

public class ItemResponse {
    private Integer itemId;
    private String name;
    private String description;
    private Integer price;
    private Integer productAmount;

    
    public ItemResponse() {
    }

    
    public ItemResponse(Integer itemId, String name, String description, Integer price, Integer productAmount) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productAmount = productAmount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }
}