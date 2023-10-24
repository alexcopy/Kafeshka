package com.kafeshka;

import com.kafeshka.exception.OrderException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private List<Order> orders;
    private HashMap<MenuItem, Integer> hashmap;


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

    public List<Order> getOrderHistory(Customer customer) {
        return this.getOrdersByCustomer(customer);
    }

    public List<MenuItem> getMostPopularMenuItem(int numberOfItems) {
        Map<MenuItem, Integer> dishOrderCount = new HashMap<>();
        for (Order order : orders) {
            for (MenuItem item : order.getItems()) {
                if (item instanceof MenuItem) {
                    dishOrderCount.put(item, dishOrderCount.getOrDefault(item, 0) + 1);
                }
            }
        }
        List<MenuItem> mostPopularDishes = new ArrayList<>(dishOrderCount.keySet());
        mostPopularDishes.sort((dish1, dish2) -> dishOrderCount.get(dish2) - dishOrderCount.get(dish1));
        return mostPopularDishes.subList(0, Math.min(numberOfItems, mostPopularDishes.size()));

    }

    public double getMeanReceipt() {
        double totalAmount = 0.0;
        int orderCount = orders.size();
        if (orderCount == 0){
            return totalAmount;
        }
        for (Order order : orders) {
            totalAmount += order.calculateTotalAmount();
        }
        DecimalFormat dF = new DecimalFormat("#.##");
        return Double.parseDouble(dF.format(totalAmount/orderCount));
    }
}

