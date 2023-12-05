package com.kafeshka.KafeshkaRS.cook_manager.service;

import com.kafeshka.KafeshkaRS.cook_manager.CookManager;
import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;
import com.kafeshka.KafeshkaRS.model.MenuItem;
import com.kafeshka.KafeshkaRS.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.List;

@Service
public class CookOrderService {
    private final CookManager cookManager;

    @Autowired
    public CookOrderService(CookManager cookManager) {
        this.cookManager = cookManager;
    }

    public void assignTasksToCooks(MenuItem menuItem) {
        // Logic to process the order and create tasks for cooks

        Task task = createTaskFromMenuItem(menuItem);

        // Use CookManager to assign the task to a suitable cook
        cookManager.assignTaskToCook(task);

        // Other order processing logic
    }

    public void processOrder(CookingOrder cookingOrder) {
        List<Order> queuedOrders = null;

        for (Order order : queuedOrders) {


            // Process each queued order, perform necessary actions
            // For example, update order status, send notifications, etc.
            // You might have something like:
            // order.setStatus(OrderStatus.PROCESSING);
            // updateOrder(order);
        }
    }

    public Task createTaskFromMenuItem(MenuItem menuItem) {
        // Extract relevant information from the order to create a task
        String taskDescription = "Prepare " + menuItem.getName(); // Assuming menuItem has dish information
        int complexity = calculateComplexity(menuItem); // Calculate complexity based on menuItem details
        boolean isUrgent = determineUrgency(menuItem); // Check if the order is urgent

        // Determine expertise required for the task based on order details
        String requiredExpertise = menuItem.getItemExpertise();
        // Create a Task object using the extracted information
        Task task = new Task(requiredExpertise, taskDescription, complexity, isUrgent);
        return task;
    }

    public int calculateComplexity(MenuItem menuItem) {
        // For example, calculate complexity based on dish type or other menuItem details
        int baseComplexity = 5; // Default base complexity value

        String dishType = menuItem.getDishType(); // Assuming there's a method to get dish type from menuItem

        // Calculate complexity based on dish type or other menuItem details
        switch (dishType) {
            case "Appetizer":
                baseComplexity += 3; // Increment complexity for appetizers
                break;
            case "Main Course":
                baseComplexity += 5; // Increment complexity for main courses
                break;
            case "Dessert":
                baseComplexity += 2; // Increment complexity for desserts
                break;
            // Add more cases or conditions based on your dish types or other menuItem details
            default:
                baseComplexity += 1;
                break;
        }

        // You can include additional logic to adjust complexity based on other factors from the menuItem

        return baseComplexity;
    }

    public boolean determineUrgency(MenuItem menuItem) {
        // Get the menuItem timestamp or creation time
        LocalDateTime menuItemTime = menuItem.getOrderTime(); // Replace getOrderTime() with your method to retrieve menuItem timestamp

        // Get the current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Calculate the time difference in minutes between the current time and the menuItem time
        long minutesDifference = ChronoUnit.MINUTES.between(menuItemTime, currentTime);

        // Determine if the order is urgent based on a specific timeframe (e.g., within the last 30 minutes)
        boolean isUrgent = minutesDifference < 30; // Consider urgent if order placed within the last 30 minutes

        return isUrgent;
    }

    public String determineRequiredExpertise(MenuItem menuItem) {
        String dishType = menuItem.getItemExpertise(); // Assuming there's a method to get the dish type from the order

        // Determine the required expertise based on the dish type
        String requiredExpertise;

        switch (dishType) {
            case "Pizza":
            case "Pasta":
                requiredExpertise = "Italian Cuisine"; // Expertise required for Italian dishes
                break;
            case "Sushi":
                requiredExpertise = "Japanese Cuisine"; // Expertise required for Japanese dishes
                break;
            case "Tacos":
                requiredExpertise = "Mexican Cuisine"; // Expertise required for Mexican dishes
                break;
            case "Burger":
                requiredExpertise = "American Cuisine"; // Expertise required for American dishes
                break;
            case "Curry":
                requiredExpertise = "Indian Cuisine"; // Expertise required for Indian dishes
                break;
            case "Stir Fry":
                requiredExpertise = "Asian Cuisine"; // Expertise required for Asian dishes
                break;
            // Add more cases for other types of dishes and their corresponding expertise
            default:
                requiredExpertise = "General Cooking"; // Default expertise for unspecified dishes
                break;
        }

        return requiredExpertise;
    }

}
