package com.kafeshka.KafeshkaRS.cook_manager.service;

import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;

import java.util.Optional;

public interface CookingOrderServiceInt {
    Optional<CookingOrder> createCookingOrder(CookingOrder cookingOrder);
    boolean deleteCookingOrder(Long id);
}
