package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.discount.Discount;
import com.kafeshka.KafeshkaRS.order.Order;

import java.util.List;

public interface DiscountServiceInt {
    void applyDiscountToOrder(Order order, Discount discount);

    void removeDiscountFromOrder(Order order);

    List<Discount> getAvailableDiscounts();
}
