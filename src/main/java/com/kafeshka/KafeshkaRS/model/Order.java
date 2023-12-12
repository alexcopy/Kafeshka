package com.kafeshka.KafeshkaRS.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kafeshka.KafeshkaRS.menu.MenuItem;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.payment.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "orders")
public class Order {

    public Order() {
        this.orderId = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "total_cooking_time_sec")
    private double totalCookingTimeSec;

    @Column(name = "tips")
    private double tips;

    @Column(name = "order_comments")
    private String orderComments;

    @Column(name = "delivery")
    private boolean delivery;

    @Column(name = "delivery_time")
    private Date deliveryTime;

    @OneToMany(mappedBy = "order")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "order" }, allowSetters = true)
    private Set<Address> deliveryAddresses = new HashSet<>();

    @GeneratedValue
    @Column(name = "order_id", unique = true, columnDefinition = "BINARY(16)")
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

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Define 'createdAt' field

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Set current timestamp before persisting the entity
    }
}
