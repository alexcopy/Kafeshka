package com.kafeshka.discount;

import com.kafeshka.order.Order;
import com.kafeshka.order.OrderManager;

import java.util.ArrayList;
import java.util.List;

public class DiscountManager {
    private List<Discount> availableDiscounts;

    public DiscountManager(List<Discount> availableDiscounts) {
        this.availableDiscounts = availableDiscounts;
    }

    public void applyDiscountToOrder(Order order, Discount discount) {
        if (availableDiscounts.contains(discount)) {
            double discountAmount = calculateDiscountAmount(order, discount);
            order.applyDiscount(discountAmount);
        } else {
            throw new IllegalArgumentException("Discount is not available");
        }

    }

    private double calculateDiscountAmount(Order order, Discount discount) {
        double discountPercentage = discount.getDiscountAmount() / 100.0;
        return order.getTotalAmount() * discountPercentage;

    }

    public void removeDiscountFromOrder (Order order){
        order.removeDiscount();
    }

    public void addedDiscount( Discount existingDiscount, String newName, double newDiscountAmount){
        if ( availableDiscounts.contains(existingDiscount)){
            existingDiscount.setName(newName);
            existingDiscount.setDiscountAmount(newDiscountAmount);
        } else {
            throw new IllegalArgumentException("Discount is not exists");
        }
    }
}
