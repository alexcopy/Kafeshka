package com.kafeshka;

import com.kafeshka.exception.OrderException;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.collections.set.ListOrderedSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Test
    void testGetMostPopularMenuItem() throws OrderException {
        Order order1 = new Order(customer, paymentMethod);
        Order order2 = new Order(customer, paymentMethod);
        //Order order3 = new Order(customer, paymentMethod);
        MenuItem menuItem1 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000);
        MenuItem menuItem2 = new MenuItem("Pasta", 40, 20.4, "with Ananas", false, true, 2000);
        MenuItem menuItem3 = new MenuItem("Salad", 20, 20.4, "with Ananas", false, true, 2000);
       // MenuItem menuItem4 = new MenuItem("Salad", 20, 20.4, "with Ananas", false, true, 2000);
        //MenuItem menuItem5 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000);
        //MenuItem menuItem6 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000);
        List<MenuItem> items1 = new ArrayList<>();
        List<MenuItem> items2 = new ArrayList<>();
        items1.add(menuItem1);
        items1.add(menuItem2);
        items1.add(menuItem3);
        items1.add(menuItem1);
        items2.add(menuItem1);
        items2.add(menuItem1);
        items2.add(menuItem3);
        order1.setItems(items1);
        order2.setItems(items2);
        orderManager.placeOrder(order1);
        orderManager.placeOrder(order2);
        List<MenuItem> mostPopularMenuItem = orderManager.getMostPopularMenuItem(3);
        assertEquals("Pizza", mostPopularMenuItem.get(0).getName());

    }
}