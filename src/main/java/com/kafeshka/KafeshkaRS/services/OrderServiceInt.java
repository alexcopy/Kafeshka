package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.order.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderServiceInt {
    Optional<List<Order>> getAllOrders();
    Optional<List<Order>> getOrdersByStatus(OrderStatus status);
    Optional<Order> createOrder(Order order);
    boolean deleteOrder(Long orderId);
    Optional<Order> getOrderById(Long orderId);
}
