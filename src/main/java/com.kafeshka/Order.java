package com.kafeshka;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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

}



