package com.kafeshka.KafeshkaRS.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


//    @ManyToOne
//    @JoinColumn(name = "menuitem_id")
//    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "menu_item_name", referencedColumnName = "name")
    private MenuItem menuItem;


    // Additional details specific to OrderItem like quantity, price, etc.
    private int quantity;
    private double price;  // Price for the specific order item


}

