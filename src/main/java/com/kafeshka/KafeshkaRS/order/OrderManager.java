package com.kafeshka.KafeshkaRS.order;

import com.kafeshka.KafeshkaRS.customer.Customer;
import com.kafeshka.KafeshkaRS.menu.MenuItem;
import com.kafeshka.KafeshkaRS.exception.OrderException;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        DecimalFormat dF = new DecimalFormat("#.##");
        int orderCount = orders.size();
        if (orderCount == 0) {
            return Double.parseDouble(dF.format(totalAmount));
        }
        for (Order order : orders) {
            totalAmount += order.calculateTotalAmount();
        }
        return Double.parseDouble(dF.format(totalAmount / orderCount));
    }


    public List<Order> getOrdersInProgress() {
        List<OrderStatus> inProgressStatuses = OrderStatus.getOrdersInProgress();
        return orders.stream()
                .filter(order -> inProgressStatuses.contains(order.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersIsQueued() {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.ORDER_QUEUED).collect(Collectors.toList());
    }

    // TODO create a new method On The Way (for loop) + test
    public List<Order> getOrdersOnTheWay() {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.ON_THEWAY).collect(Collectors.toList());
    }

    public int getTotalOrderInProgressTotalCookingTimeSec() {
        return this.getOrdersInProgress().stream()
                .mapToInt(Order::getTotalOrderCookingTimeSec)
                .sum();
    }
}

