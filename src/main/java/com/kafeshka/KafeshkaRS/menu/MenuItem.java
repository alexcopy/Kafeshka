package com.kafeshka.KafeshkaRS.menu;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "weight_or_value")
    private double weightOrValue;

    @Column(name = "description")
    private String description;

    @Column(name = "vegetarian")
    private boolean vegetarian;

    @Column(name = "spicy")
    private boolean spicy;

    @Column(name = "calories")
    private int calories;

    @Column(name = "cooking_time_sec")
    private int cookingTimeSec;

}
