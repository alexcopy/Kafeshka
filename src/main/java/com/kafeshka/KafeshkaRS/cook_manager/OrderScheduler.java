package com.kafeshka.KafeshkaRS.cook_manager;

import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;
import com.kafeshka.KafeshkaRS.cook_manager.repository.CookingOrderRepo;
import com.kafeshka.KafeshkaRS.cook_manager.service.CookOrderService;
import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

import java.util.List;

@Component
public class OrderScheduler {

    private final CookOrderService cookOrderService;
    private CookingOrderRepo orderRepository;


    // Constructor injection for OrderRepository and CookOrderService
    public OrderScheduler(CookingOrderRepo orderRepository, CookOrderService cookOrderService) {
        this.orderRepository = orderRepository;
        this.cookOrderService = cookOrderService;
    }

    @Scheduled(fixedRate = 5000) // Runs every 5 seconds
    public void filterQueuedOrders() {
        Optional<CookingOrder> queuedOrder = retrieveQueuedOrderFromRepository();
        queuedOrder.ifPresent(cookOrderService::processOrder);
    }


    private Optional<CookingOrder> retrieveQueuedOrderFromRepository() {
        return orderRepository.findFirstByStatusOrderByCreatedAtAsc(OrderStatus.ORDER_QUEUED);
    }

}
