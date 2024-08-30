package com.damazon.model;

import javax.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walletid")
    private Long id;

    @Column(name = "card_number")
    private Integer cardNum;  

    @Column(name = "card_expiration")
    private int expiYear; 

    @Column(name = "cardcvv")
    private int cvv;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private User user; 
    //Link it to User table/obj
    
    public Wallet() {}

    
    public Wallet(int cardNum, int expiYear, int cvv, User user) {
        this.cardNum = cardNum;
        this.expiYear = expiYear;
        this.cvv = cvv;
        this.user = user;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public int getExpiYear() {
        return expiYear;
    }

    public void setExpiYear(int expiYear) {
        this.expiYear = expiYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
