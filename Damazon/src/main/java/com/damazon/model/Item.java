package com.damazon.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @Column(name = "itemID")
    private Integer itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "imgLocation")
    private String imgLocation;

    // Constructors
    public Item() {
    }

    public Item(Integer itemId, String name, Integer price, String description, Integer rating, String imgLocation) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.imgLocation = imgLocation;
    }

    // Getters
    public Integer getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRating() {
        return rating;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    // Setters
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }
}
