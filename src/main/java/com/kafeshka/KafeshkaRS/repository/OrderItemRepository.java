package com.kafeshka.KafeshkaRS.repository;

import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
    Optional<OrderItem> findByName(String name);
}
