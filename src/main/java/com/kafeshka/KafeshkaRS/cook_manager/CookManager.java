package com.kafeshka.KafeshkaRS.cook_manager;

import com.kafeshka.KafeshkaRS.cook_manager.service.Cook;
import com.kafeshka.KafeshkaRS.cook_manager.service.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CookManager {
    // Suppose you have a list of available cooks in the kitchen
    // For simplicity, let's assume a list of Cook objects
    private List<Cook> availableCooks;

    // Method to assign a task to a suitable cook based on parameters
    public void assignTaskToCook(Task task) {
        Cook suitableCook = findSuitableCook(task);
        if (suitableCook != null) {
            // Assign the task to the suitable cook
            suitableCook.addTask(task);
            System.out.println("Task assigned to cook: " + suitableCook.getName());
        } else {
            System.out.println("No suitable cook available for the task: " + task.getDescription());
        }
    }

    // Logic to find a suitable cook based on expertise, availability, and workload
    private Cook findSuitableCook(Task task) {
        // Sort available cooks by criteria such as expertise, availability, workload, etc.
        List<Cook> sortedCooks = sortCooksByCriteria(task);

        // Return the first suitable cook from the sorted list
        for (Cook cook : sortedCooks) {
            if (isCookSuitable(cook, task)) {
                return cook;
            }
        }
        return null; // If no suitable cook is found
    }

    // Method to sort available cooks by criteria
    private List<Cook> sortCooksByCriteria(Task task) {
        // Implement sorting logic based on expertise, availability, workload, etc.
        // For example, using Comparator or custom sorting algorithms

        // Return a sorted list of available cooks
        return availableCooks; // Replace this with your actual sorting logic
    }

    // Method to check if a cook is suitable for the task based on defined criteria
    private boolean isCookSuitable(Cook cook, Task task) {
        // Implement logic to check cook's expertise, availability, workload, etc.
        // For example:
        // Check if cook's expertise matches task requirements
        // Check if cook is available to take on another task
        // Check if cook's workload is manageable

        // Return true if the cook is suitable for the task; otherwise, return false
        return cook.getExpertise().equals(task.getRequiredExpertise())
                && cook.isAvailable() && isWorkloadManageable(cook);
    }

    // Example method to check if a cook's workload is manageable
    private boolean isWorkloadManageable(Cook cook) {
        // Implement logic to determine if the cook's workload is manageable
        // Return true if the workload is manageable; otherwise, return false
        return true; // Replace this with your actual workload management logic
    }
}
