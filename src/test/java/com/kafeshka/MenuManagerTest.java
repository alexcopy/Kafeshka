package com.kafeshka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerTest {
    private MenuManager menuManager;

    @BeforeEach
    void setUp() {
        this.menuManager = new MenuManager();
    }

    @Test
    void addMenuItem() {
        MenuItem menuItem = new MenuItem("Margarita", 2.13, 500, "italien pizza", false, true, 5000);
        this.menuManager.addMenuItem(menuItem);
        assertTrue(this.menuManager.getMenuItems().contains(menuItem));
    }

    @Test
    void removeMenuItem() {
        MenuItem menuItem = new MenuItem("Margarita", 2.13, 500, "italien pizza", false, true, 5000);
 //ToDo add test here
        this.menuManager.removeMenuItem(menuItem);
        assertFalse(this.menuManager.getMenuItems().contains(menuItem));
    }
}