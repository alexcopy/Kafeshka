package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInt {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> createOrder(Order order) {
        try {
            Order savedOrder = orderRepository.save(order);
            return Optional.ofNullable(savedOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        // Implement any validation or specific logic before updating
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }
        return null; // Or throw an exception if needed
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Optional<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        // Return an Optional of the orders list
        return Optional.of(orders);
    }

    @Override
    public Optional<List<Order>> getOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findByStatus(status);
        return Optional.ofNullable(orders.isEmpty() ? null : orders);
    }
}

