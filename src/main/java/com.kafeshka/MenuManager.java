package com.kafeshka;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private List<MenuItem> itemList;

    public MenuManager() {
        this.itemList = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        this.itemList.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        this.itemList.remove(item);
    }
    public List getMenuItems() {
        return this.itemList;
    }
}

