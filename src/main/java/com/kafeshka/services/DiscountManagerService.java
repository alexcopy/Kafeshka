package com.kafeshka.services;

import com.kafeshka.discount.Discount;
import com.kafeshka.discount.DiscountManager;
import com.kafeshka.order.Order;

import java.util.List;

public class DiscountManagerService implements DiscountServiceInt {
    private DiscountManager discountManager;

    public DiscountManagerService(DiscountManager discountManager) {
        discountManager = discountManager;
    }

    @Override
    public void applyDiscountToOrder(Order order, Discount discount) {
        discountManager.applyDiscountToOrder(order, discount);
    }

    @Override
    public void removeDiscountFromOrder(Order order) {
        discountManager.removeDiscountFromOrder(order);
    }

    @Override
    public List<Discount> getAvailableDiscounts() {
        return discountManager.getAvailableDiscounts();
    }
}
