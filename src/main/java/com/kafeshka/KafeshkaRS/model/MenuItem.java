package com.kafeshka.KafeshkaRS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double weightOrValue;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean vegetarian;
    @Column(nullable = false)
    private boolean spicy;
    @Column(nullable = false)
    private int calories;
    @Column(nullable = false)
    private String itemExpertise;
    @Column(nullable = false)
    private String dishType;
    @Column(nullable = false)
    private int cookingTimeSec;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Define 'createdAt' field

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Set current timestamp before persisting the entity
    }

}
