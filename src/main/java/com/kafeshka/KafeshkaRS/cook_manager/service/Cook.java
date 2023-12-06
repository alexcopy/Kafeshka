package com.kafeshka.KafeshkaRS.cook_manager.service;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
@Getter
@Setter
public class Cook {
    private String name;
    private String expertise;
    private boolean available;
    private List<Task> taskList;

    public Cook(String name, String expertise) {
        this.name = name;
        this.expertise = expertise;
        this.available = true; // Assuming cook is available by default
        this.taskList = new ArrayList<>();
    }
    public void addTask(Task task) {
        this.taskList.add(task);
    }
}

