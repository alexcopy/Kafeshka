package com.kafeshka.services;

import com.kafeshka.discount.Discount;
import com.kafeshka.order.Order;

import java.util.List;

public class DiscountManagerService implements DiscountServiceInt {

    @Override
    public void applyDiscountToOrder(Order order, Discount discount) {

    }

    @Override
    public void removeDiscountFromOrder(Order order) {

    }

    @Override
    public List<Discount> getAvailableDiscounts() {
        return null;
    }
}
