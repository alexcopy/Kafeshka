package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.discount.Discount;
import com.kafeshka.KafeshkaRS.discount.DiscountManager;
import com.kafeshka.KafeshkaRS.order.Order;

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
