package com.damazon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")  // Ensure the table name matches exactly with your database schema
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;
    
    @Column(name = "userName", unique = true, nullable = false)
    private String username;  

    @Column(name = "password", nullable = false)
    private String password;  

    @Column(name = "isAdmin", nullable = false)
    private int isAdmin;  // 0 = user ,  1 = Admin

    @Column(name = "walletId")
    private Integer walletId;  // Additional field to match your database structure

    // Constructors
    public User() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return (isAdmin!=0) ;
    }

    public void setAdmin(boolean admin) {
    	this.isAdmin = admin ? 1 : 0;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    
}
