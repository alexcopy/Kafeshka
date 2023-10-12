package com.kafeshka;

import com.kafeshka.exception.OrderException;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    List<Order> orders;

    public OrderManager(List<Order> orders) {
        orders = new ArrayList<>();
    }

    public void placeOrder(Order order) throws OrderException {
        if (order == null || orders.contains(order)) {
            throw new OrderException("Order is empty or already exists");
        }
        orders.add(order);
    }

    public void cancelOrder(Order order) throws OrderException {
        if (order == null || !orders.contains(order)){
            throw new OrderException("Order is empty or order mot exists");
        }
        orders.remove(order);
    }
}
