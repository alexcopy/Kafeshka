package com.kafeshka.KafeshkaRS.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
@Setter
@Getter
public class Discount {
    private String name;
    private double discountAmount;

    public Discount(String name, double discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
    }
}


