package com.kafeshka.KafeshkaRS.discount;

import com.kafeshka.KafeshkaRS.order.Order;

import java.util.List;


public class DiscountManager {
    private List<Discount> availableDiscounts;

    public DiscountManager(List<Discount> availableDiscounts) {
        this.availableDiscounts = availableDiscounts;
    }

    public List<Discount> getAvailableDiscounts() {
        return availableDiscounts;
    }

    public void applyDiscountToOrder(Order order, Discount discount) {
        if (availableDiscounts.contains(discount)) {
            double discountAmount = calculateDiscountAmount(order, discount);
            order.applyDiscount(discountAmount);
        } else {
            throw new IllegalArgumentException("Discount is not available");
        }
    }

    public void applyCumulativeDiscount(Order order, Discount discount) {
        if (availableDiscounts.contains(discount)) {
            double existDiscount = order.getDiscount();
            double finalDiscount = existDiscount + discount.getDiscountAmount();
            Discount totalDiscount = new Discount("CumulativeDiscount", finalDiscount);
            double cumulativeAmountDiscount = calculateDiscountAmount(order, totalDiscount);
            order.applyDiscount(cumulativeAmountDiscount);
        } else {
            throw new IllegalArgumentException("Discount is not available");
        }
    }


    private double calculateDiscountAmount(Order order, Discount discount) {
        double discountPercentage = discount.getDiscountAmount() / 100.0;
        return order.getTotalAmount() * discountPercentage;

    }

    public void removeDiscountFromOrder(Order order) {
        order.removeDiscount();
    }

    public void editDiscount(Discount existingDiscount, String newName, double newDiscountAmount) {
        if (availableDiscounts.contains(existingDiscount)) {
            existingDiscount.setName(newName);
            existingDiscount.setDiscountAmount(newDiscountAmount);
        } else {
            throw new IllegalArgumentException("Discount is not exists");
        }
    }
}
