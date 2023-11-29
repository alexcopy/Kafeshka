package com.kafeshka.KafeshkaRS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private int cookingTimeSec;
}
