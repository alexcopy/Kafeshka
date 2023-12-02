package com.kafeshka;

import com.kafeshka.KafeshkaRS.customer.Customer;
import com.kafeshka.KafeshkaRS.exception.OrderException;
import com.kafeshka.KafeshkaRS.menu.MenuItem;
import com.kafeshka.KafeshkaRS.order.Order;
import com.kafeshka.KafeshkaRS.order.OrderManager;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.payment.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        paymentMethod = PaymentMethod.DEBIT_CARD;

    }

    @Test
    void testPlaceOrder() throws OrderException {
        Order order = new Order(customer, paymentMethod);
        assertEquals("Debit Card", paymentMethod.getDisplayName());
        paymentMethod = PaymentMethod.GOOGLE_PAY;
        order.setPaymentMethod(paymentMethod);
        assertEquals("Google Pay", paymentMethod.getDisplayName());
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
        MenuItem menuItem1 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000, 120);
        MenuItem menuItem2 = new MenuItem("Pasta", 40, 20.4, "with Ananas", false, true, 2000, 120);
        MenuItem menuItem3 = new MenuItem("Salad", 20, 20.4, "with Ananas", false, true, 2000, 120);
        // MenuItem menuItem4 = new MenuItem("Salad", 20, 20.4, "with Ananas", false, true, 2000,120);
        //MenuItem menuItem5 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000,120);
        //MenuItem menuItem6 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000,120);
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

    @Test
    void testMeanReceipt() throws OrderException {
        Order order1 = new Order(customer, paymentMethod);
        Order order2 = new Order(customer, paymentMethod);
        Order order3 = new Order(customer, paymentMethod);
        MenuItem menuItem1 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000, 120);
        MenuItem menuItem2 = new MenuItem("Pasta", 40, 20.4, "with Ananas", false, true, 2000, 120);
        MenuItem menuItem3 = new MenuItem("Salad", 60, 20.4, "with Ananas", false, true, 2000, 120);
        List<MenuItem> items1 = new ArrayList<>();
        List<MenuItem> items2 = new ArrayList<>();
        List<MenuItem> items3 = new ArrayList<>();
        items1.add(menuItem1);
        items2.add(menuItem2);
        items3.add(menuItem3);
        order1.setItems(items1);
        order2.setItems(items2);
        order3.setItems(items3);
        orderManager.placeOrder(order1);
        orderManager.placeOrder(order2);
        orderManager.placeOrder(order3);
        double meanReceipt = orderManager.getMeanReceipt();
        assertEquals(40.0, meanReceipt, 0.001);
    }

    @Test
    void testAddItemToOrder() {
        Order order = new Order(customer, paymentMethod);
        MenuItem menuItem = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000, 120);
        order.addNewItemToOrder(menuItem);
        assertTrue(order.getItems().contains(menuItem));
    }

    @Test
    void testAllArgsConstructor() {
        //  new newOrder(double 300, String orderComments, boolean delivery, Date deliveryTime, String deliveryAddress, Customer customer, double discount, PaymentMethod paymentMethod) {
//);
    }

    @Test
    void testGetInProgressOrders() throws OrderException {
        List<Order> ordersInProgress = this.orderManager.getOrdersInProgress();
        Order order1 = new Order(customer, paymentMethod);
        Order order2 = new Order(customer, paymentMethod);
        Order order3 = new Order(customer, paymentMethod);
        MenuItem menuItem1 = new MenuItem("Pizza", 20, 20.4, "with Ananas", false, true, 2000, 120);
        MenuItem menuItem2 = new MenuItem("Pasta", 40, 20.4, "with Ananas", false, true, 2000, 120);
        MenuItem menuItem3 = new MenuItem("Salad", 60, 20.4, "with Ananas", false, true, 2000, 120);
        List<MenuItem> items1 = new ArrayList<>();
        List<MenuItem> items2 = new ArrayList<>();
        List<MenuItem> items3 = new ArrayList<>();
        items1.add(menuItem1);
        items2.add(menuItem2);
        items3.add(menuItem3);
        order1.setItems(items1);
        order1.setStatus(OrderStatus.ON_THEWAY);
        order2.setItems(items2);
        order3.setItems(items3);
        orderManager.placeOrder(order1);
        orderManager.placeOrder(order2);
        orderManager.placeOrder(order3);
        assertEquals(2, this.orderManager.getOrdersInProgress().size());
        assertEquals(1, this.orderManager.getOrdersOnTheWay().size());
        int cookingTimeSec = this.orderManager.getTotalOrderInProgressTotalCookingTimeSec();
        assertEquals(240, cookingTimeSec);
    }
}