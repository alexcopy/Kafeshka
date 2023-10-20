package com.kafeshka;

import com.kafeshka.exception.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

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
    void  testPlaceOrder() throws OrderException {
        Order order = new Order(customer, paymentMethod);
        orderManager.placeOrder(order);
        assertEquals(1,orderManager.getOrders().size() );
    }
    @Test
    void  testPlaceOrderWithException()   {

        try {
            orderManager.placeOrder(null);
        } catch (OrderException e) {
          assertEquals("Order is empty or already exists", e.getMessage());
        }
        assertEquals(0,orderManager.getOrders().size() );
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void getOrdersByCustomer() {
    }

    @Test
    void updateOrderStatus() {
    }

    @Test
    void getOrders() {
    }

    @Test
    void getOrderHistory() {
    }
}