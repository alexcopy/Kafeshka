package com.kafeshka.KafeshkaRS.menu;

import com.kafeshka.KafeshkaRS.exception.MenuException;

import java.util.ArrayList;
import java.util.List;

public class MenuManager implements MenuInt {
    private final List<MenuItem> itemList;

    public MenuManager() {
        this.itemList = new ArrayList<>();
    }

    @Override
    public void addMenuItem(MenuItem item) throws MenuException {
        if (itemList.contains(item)){
            throw new MenuException("The item is already in the list!");
        }
        this.itemList.add(item);
    }

    @Override
    public void removeMenuItem(MenuItem item) throws MenuException {
        if (!itemList.contains(item)) {
            throw new MenuException("The item does not exist in the list!");
        }
        this.itemList.remove(item);
    }

    public List<MenuItem> getMenuItems() {
        return this.itemList;
    }
}

