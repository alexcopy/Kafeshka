package com.kafeshka.KafeshkaRS.menu;

import com.kafeshka.KafeshkaRS.exception.MenuException;

import java.util.List;

public interface MenuInt {
    List<MenuItem> getMenuItems();
   void addMenuItem(MenuItem item) throws MenuException;
   void removeMenuItem(MenuItem item)throws MenuException;
}
