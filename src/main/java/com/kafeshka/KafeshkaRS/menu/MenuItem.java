package com.kafeshka.KafeshkaRS.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class MenuItem{
    private String name;
    private double price;
    private double weightOrValue;
    private String description;
    private boolean vegetarian;
    private boolean spicy;
    private int calories;
    private int cookingTimeSec;
}
