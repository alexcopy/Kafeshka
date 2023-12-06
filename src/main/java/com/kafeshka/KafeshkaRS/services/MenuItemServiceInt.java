package com.kafeshka.KafeshkaRS.services;
import com.kafeshka.KafeshkaRS.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemServiceInt {
    List<MenuItem> getAllMenuItems();
    Optional<MenuItem> getMenuItemById(Long id);
    Optional<MenuItem> createMenuItem(MenuItem menuItem);
    boolean deleteMenuItem(Long id);

}
