package com.kafeshka.KafeshkaRS.web.controllers.api;

import com.kafeshka.KafeshkaRS.model.MenuItem;
import com.kafeshka.KafeshkaRS.services.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemServiceImpl menuItemService;

    @Autowired
    public MenuItemController(MenuItemServiceImpl menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);

        return menuItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createMenuItem(@RequestBody MenuItem menuItem) {
        Optional<MenuItem> createdMenuItem = menuItemService.createMenuItem(menuItem);

        return createdMenuItem.map(value -> new ResponseEntity<>("MenuItem created successfully", HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>("Failed to create MenuItem", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        boolean deleted = menuItemService.deleteMenuItem(id);

        if (deleted) {
            return new ResponseEntity<>("MenuItem deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete MenuItem", HttpStatus.NOT_FOUND);
        }
    }
}

