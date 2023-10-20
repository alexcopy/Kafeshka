package com.kafeshka;

import com.kafeshka.exception.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    private OrderManager orderManager;
    private Customer customer;
    private PaymentMethod paymentMethod;

    @BeforeEach
    void setUp() {
        orderManager = new OrderManager();
        customer = new Customer(
                "David",
                "23456789",
                "Böhmersweg 29",
                new Date()
        );
        paymentMethod = new PaymentMethod("MasterCard");
    }

    @Test
    void testPlaceOrder() throws OrderException {
        Order order = new Order(customer, paymentMethod);
        orderManager.placeOrder(order);
        assertEquals(1, orderManager.getOrders().size());
    }

    @Test
    void testPlaceOrderWithException() {

        try {
            orderManager.placeOrder(null);
        } catch (OrderException e) {
            assertEquals("Order is empty or already exists", e.getMessage());
        }
        assertEquals(0, orderManager.getOrders().size());
    }

    @Test
    void testCancelOrder() throws OrderException {
        Order order = new Order(customer, paymentMethod);
        orderManager.placeOrder(order);
        assertEquals(1, orderManager.getOrders().size());
        orderManager.cancelOrder(order);
        assertEquals(0, orderManager.getOrders().size());

    }

    @Test
    void getOrdersByCustomer() throws OrderException {
        Order order1 = new Order(customer, paymentMethod);
        Order order2 = new Order(customer, paymentMethod);
        Order order3 = new Order(customer, paymentMethod);
        orderManager.placeOrder(order1);
        orderManager.placeOrder(order2);
        orderManager.placeOrder(order3);
        List<Order> ordersByCustomer = orderManager.getOrdersByCustomer(customer);
        assertEquals(3, ordersByCustomer.size());
    }

    @Test
    void testUpdateOrderStatus() throws OrderException {
        Order order = new Order(customer, paymentMethod);
        orderManager.placeOrder(order);
        orderManager.updateOrderStatus(order, OrderStatus.DELIVERED);
        assertEquals(OrderStatus.DELIVERED, order.getStatus());

    }

    @Test
    void testGetOrders() throws OrderException {
        Order order1 = new Order(customer, paymentMethod);
        Order order2 = new Order(customer, paymentMethod);
        Order order3 = new Order(customer, paymentMethod);
        orderManager.placeOrder(order1);
        orderManager.placeOrder(order2);
        orderManager.placeOrder(order3);
        List<Order> orders = orderManager.getOrders();
        assertEquals(3, orders.size());

    }

}