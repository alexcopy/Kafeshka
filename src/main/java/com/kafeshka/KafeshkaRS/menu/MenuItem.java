package com.kafeshka.KafeshkaRS.menu;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MenuItem{
    private String name;
    private String dishType;
    private String itemExpertise;
    private double price;
    private double weightOrValue;
    private String description;
    private boolean vegetarian;
    private boolean spicy;
    private int calories;
    private int cookingTimeSec;
    private LocalDateTime orderTime;
}
