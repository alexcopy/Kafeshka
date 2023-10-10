package com.kafeshka;


import java.util.Date;
import java.util.List;

public class Order {
    private List<MenuItem> items;
    private double totalAmount;
    private double tips;
    private String orderComments;
    private Date orderDate;
    private boolean delivery;
    private Date deliveryTime;
    private String deliveryAddress;
    private int orderID;
    private Customer customer;
    private OrderStatus status;
    private Discount discount;
    private PaymentMethod paymentMethod;

    public Order(List<MenuItem> items, double totalAmount, double tips, String orderComments, Date orderDate, boolean delivery, Date deliveryTime, String deliveryAddress, int orderID, Customer customer, OrderStatus status, Discount discount, PaymentMethod paymentMethod) {
        this.items = items;
        this.totalAmount = totalAmount;
        this.tips = tips;
        this.orderComments = orderComments;
        this.orderDate = orderDate;
        this.delivery = delivery;
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.orderID = orderID;
        this.customer = customer;
        this.status = status;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
    }


}
