package com.kafeshka;

import com.kafeshka.KafeshkaRS.exception.MenuException;
import com.kafeshka.KafeshkaRS.menu.MenuItem;
import com.kafeshka.KafeshkaRS.menu.MenuManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuIntManagerTest {
    private MenuManager menuManager;

    @BeforeEach
    void setUp() {
        this.menuManager = new MenuManager();
    }

    @Test
    void addMenuItem() {
        MenuItem menuItem = new MenuItem("Margarita", 2.13, 500, "italien pizza", false, true, 5000,120);
        try {
            this.menuManager.addMenuItem(menuItem);
        } catch (MenuException e) {
            fail("Unexpected  behavior in addItem" + e.getMessage());
        }
        assertTrue(this.menuManager.getMenuItems().contains(menuItem));
    }

    @Test
    void removeMenuItem() {
        MenuItem menuItem = new MenuItem("Margarita", 2.13, 500, "italien pizza", false, true, 5000,120);
        try {
            this.menuManager.addMenuItem(menuItem);
            this.menuManager.removeMenuItem(menuItem);
        } catch (MenuException e) {
            fail("Unexpected  behavior in removeItem" + e.getMessage());
        }
        assertFalse(this.menuManager.getMenuItems().contains(menuItem));
    }

    @Test
    void testAddDuplicateMenuItem() {
        MenuItem menuItem = new MenuItem("Margarita", 2.13, 500, "italien pizza", false, true, 5000,120);
        try {
            this.menuManager.addMenuItem(menuItem);
            this.menuManager.addMenuItem(menuItem);
        } catch (MenuException e) {
            assertEquals("The item is already in the list!", e.getMessage());
        }

    }

    @Test
    void testRemoveNonExistingMenuItem() {
        MenuItem menuItem = new MenuItem("Margarita", 2.13, 500, "italien pizza", false, true, 5000,120);
        try {
            this.menuManager.removeMenuItem (menuItem);
        } catch (MenuException e) {
            assertEquals("The item does not exist in the list!", e.getMessage());
        }
    }

}