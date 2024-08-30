package com.damazon.model;

import javax.persistence.*;

@Entity
@Table(name = "users")  
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;  

    @Column(name = "password", nullable = false)
    private String password;  

    @Column(name = "is_admin", nullable = false)
    private int isAdmin;  // 0 = user ,  1 = Admin

    @Column(name = "wallet_id")
    private Integer walletId;  


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
