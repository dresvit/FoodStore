package com.foodstore.entity;

import java.io.Serializable;

public class Food implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String foodname;
    private String price;
    private int quantity;
    private boolean isvip;
    private String description;
    
    public Food() {}
    
    public Food(Food food) {
    	this.id = food.id;
    	this.foodname = food.foodname;
    	this.price = food.price;
    	this.quantity = food.quantity;
    	this.isvip = food.isvip;
    	this.description = food.description;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(boolean isvip) {
        this.isvip = isvip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
