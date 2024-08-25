package com.damazon.model;

import javax.persistence.*;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNum;
    private int expiMonth;
    private int expiYear;
    private int cvv;

    // Constructors, getters, and setters
    public Wallet() {}

    public Wallet(String cardNum, int expiMonth, int expiYear, int cvv) {
        this.cardNum = cardNum;
        this.expiMonth = expiMonth;
        this.expiYear = expiYear;
        this.cvv = cvv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getExpiMonth() {
        return expiMonth;
    }

    public void setExpiMonth(int expiMonth) {
        this.expiMonth = expiMonth;
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
}
