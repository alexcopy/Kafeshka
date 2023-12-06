package com.kafeshka.KafeshkaRS.cook_manager;

import com.kafeshka.KafeshkaRS.cook_manager.service.CookingOrderService;
import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DataSyncScheduler {

    private final OrderService orderService;
    private final CookingOrderService cookingOrderService;

    @Autowired
    public DataSyncScheduler(OrderService orderService, CookingOrderService cookingOrderService) {
        this.orderService = orderService;
        this.cookingOrderService = cookingOrderService;
    }

    @Scheduled(fixedRate = 4000) // Run every 4 seconds (adjust as needed)
    public void syncData() {
        // Retrieve data by status  from Order table
        Optional<List<Order>> optionalOrders = orderService.getOrdersByStatus(OrderStatus.ORDER_QUEUED);
        System.out.println(" Perform synchronization logic of two tables: " + optionalOrders.isPresent() + " total orders to sync");
        optionalOrders.ifPresent(orders -> {
            for (Order order : orders) {
                cookingOrderService.syncWithOrder(order);
                order.setStatus(OrderStatus.IN_PROGRESS);
                this.orderService.updateOrder(order.getId(), order);
            }
        });
    }
}
