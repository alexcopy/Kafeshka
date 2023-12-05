package com.kafeshka.KafeshkaRS.cook_manager.model;


import com.kafeshka.KafeshkaRS.model.Customer;
import com.kafeshka.KafeshkaRS.model.OrderItem;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.payment.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cooking_orders")
public class CookingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "total_cooking_time_sec")
    private double totalCookingTimeSec;

    @Column(name = "tips")
    private double tips;

    @Column(name = "order_comments")
    private String orderComments;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery")
    private boolean delivery;

    @Column(name = "delivery_time")
    private Date deliveryTime;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_id", unique = true)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "discount")
    private double discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

}
