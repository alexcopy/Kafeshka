package com.kafeshka.KafeshkaRS.cook_manager.model;


import com.kafeshka.KafeshkaRS.model.OrderItem;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.payment.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(name = "menu_items", columnDefinition = "TEXT")
    private String menuItems;

    @Column(name = "total_cooking_time_sec")
    private double totalCookingTimeSec;

    @Column(name = "order_comments")
    private String orderComments;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery")
    private boolean delivery;

    @Column(name = "delivery_time")
    private Date deliveryTime;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_id", unique = true)
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerID;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Define 'createdAt' field

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Set current timestamp before persisting the entity
    }
}
