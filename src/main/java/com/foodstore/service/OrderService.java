package com.foodstore.service;

import com.foodstore.entity.Order;

public interface OrderService {

    public long createOrder(Order order);
    public void deleteOrder(Long orderId);
    public Order findOne(Long orderId);
    public long submitOrder(String customer, String amount);
}
