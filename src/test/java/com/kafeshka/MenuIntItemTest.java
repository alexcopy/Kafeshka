package com.kafeshka;

import com.kafeshka.KafeshkaRS.menu.MenuItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MenuIntItemTest {
    @Test
    void testMenuItem() {
        LocalDateTime nowt = LocalDateTime.now();
        MenuItem menuItem = new MenuItem("Margarita", "","", 2.13, 500, "italien pizza", false, true, 5000, 120, nowt);
        assertEquals("Margarita", menuItem.getName());
        assertEquals("italien pizza", menuItem.getDescription());
        assertEquals(2.13, menuItem.getPrice());
        assertEquals(500, menuItem.getWeightOrValue());
        assertEquals(5000, menuItem.getCalories());
        assertTrue(menuItem.isSpicy());
        assertFalse(menuItem.isVegetarian());
        assertEquals(120, menuItem.getCookingTimeSec());


    }
}