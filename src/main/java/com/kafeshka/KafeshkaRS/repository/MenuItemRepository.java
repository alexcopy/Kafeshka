package com.kafeshka.KafeshkaRS.repository;

import com.kafeshka.KafeshkaRS.model.MenuItem;
import com.kafeshka.KafeshkaRS.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // Custom query method to find menu items by name
    Optional<MenuItem> findByName(String name);

    // Custom query method to find vegetarian menu items
    List<MenuItem> findByVegetarianTrue();

    // Custom query method to find spicy menu items
    List<MenuItem> findBySpicyTrue();
    Optional<List<MenuItem>> findAllByIdIn(List<Long> ids);

}