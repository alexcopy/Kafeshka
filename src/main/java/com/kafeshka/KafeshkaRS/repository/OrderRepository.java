package com.kafeshka.KafeshkaRS.repository;

import com.kafeshka.KafeshkaRS.model.Customer;
import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);

    // Custom query method to find orders by status
    List<Order> findByStatus(OrderStatus status);
}
