package com.kafeshka.services;

import com.kafeshka.discount.Discount;
import com.kafeshka.order.Order;

import java.util.List;

public interface DiscountServiceInt {
    void applyDiscountToOrder(Order order, Discount discount);

    void removeDiscountFromOrder(Order order);

    List<Discount> getAvailableDiscounts();
}
