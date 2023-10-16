package com.kafeshka;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

public class Order {
    private List<MenuItem> items;
    private double totalAmount;
    private double tips;
    private String orderComments;
    private Date orderDate;
    private boolean delivery;
    private Date deliveryTime;
    private String deliveryAddress;
    private UUID orderID;
    private Customer customer;
    private OrderStatus status;
    private Discount discount;
    private PaymentMethod paymentMethod;

    public Order(double totalAmount, String orderComments, boolean delivery, Date deliveryTime, String deliveryAddress, Customer customer, Discount discount, PaymentMethod paymentMethod) {
        this.items = new ArrayList<>();
        this.totalAmount = totalAmount;
        this.tips = 0.0;
        this.orderComments = orderComments;
        this.orderDate = new Date();
        this.delivery = delivery;
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.orderID = UUID.randomUUID();
        this.customer = customer;
        this.status = OrderStatus.IN_PROGRESS;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
    }

    public static void main(String[] args) {
        Order order = new Order(
                // new ArrayList<>(),
                10.8,
                //0.0,
                "dfhgkjukl",
                // new Date(),
                true,
                new Date(),
                "dhrfdkuzfki",
                //  UUID.randomUUID(),
                new Customer(),
                // OrderStatus.IN_PROGRESS,
                new Discount(),
                new PaymentMethod()
        );
        order.setTips(13.9);

    }
}



