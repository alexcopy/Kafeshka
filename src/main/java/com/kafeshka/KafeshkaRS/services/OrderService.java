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



    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        // Implement any additional business logic if needed
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        // Implement any validation or specific logic before updating
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }
        return null; // Or throw an exception if needed
    }

    public void deleteOrder(Long id) {
        // Implement any additional logic before deletion
        orderRepository.deleteById(id);
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

