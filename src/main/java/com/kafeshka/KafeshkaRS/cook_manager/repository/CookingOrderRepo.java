package com.kafeshka.KafeshkaRS.cook_manager.repository;

import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CookingOrderRepo extends JpaRepository<CookingOrder, Long> {
    Optional<CookingOrder> findFirstByStatusOrderByCreatedAtAsc(OrderStatus status);
}
