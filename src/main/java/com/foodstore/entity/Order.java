package com.foodstore.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String customer;
    private String amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
