package com.kafeshka;

import com.kafeshka.exception.OrderException;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;


    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void placeOrder(Order order) throws OrderException {
        if (order == null || orders.contains(order)) {
            throw new OrderException("Order is empty or already exists");
        }
        orders.add(order);
    }

    public void cancelOrder(Order order) throws OrderException {
        if (order == null || !orders.contains(order)) {
            throw new OrderException("Order is empty or order not exists");
        }
        orders.remove(order);
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getCustomer().equals(customer)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) throws OrderException {
        if (order == null || !orders.contains(order)) {
            throw new OrderException("Order is empty or order not exists");
        }
        order.setStatus(newStatus);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getOrderHistory(Customer customer){
        return this.getOrdersByCustomer(customer);
    }
}

