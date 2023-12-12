package com.kafeshka.KafeshkaRS.cook_manager.service;

import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;
import com.kafeshka.KafeshkaRS.cook_manager.repository.CookingOrderRepo;
import com.kafeshka.KafeshkaRS.model.MenuItem;
import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.model.OrderItem;
import com.kafeshka.KafeshkaRS.repository.OrderItemRepository;
import com.kafeshka.KafeshkaRS.services.MenuItemServiceImpl;
import com.kafeshka.KafeshkaRS.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CookingOrderService implements CookingOrderServiceInt {

    private final CookingOrderRepo cookingOrderRepository;
    private final MenuItemServiceImpl menuItemService;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public CookingOrderService(CookingOrderRepo cookingOrderRepository, OrderItemRepository orderItemRepository, MenuItemServiceImpl menuItemService) {
        this.cookingOrderRepository = cookingOrderRepository;
        this.menuItemService = menuItemService;
        this.orderItemRepository = orderItemRepository;
    }

    public void syncWithOrder(Order order) {
        // Create or update CookingOrder based on Order details

        List<OrderItem> orderItems = order.getOrderItems();

        CookingOrder cookingOrder = cookingOrderRepository.findByOrderId(order.getId());

        List<MenuItem> menuItems = orderItems.stream()
                .map(OrderItem::getMenuItem)
                .toList();
        Optional<List<MenuItem>> optionalMenuItems = Optional.ofNullable(menuItems.isEmpty() ? null : menuItems);
        if (optionalMenuItems.isPresent()) {
            System.out.println("!!!!!! find optionalMenuItems    !!!!");
            System.out.println(optionalMenuItems);
        }else {
            System.out.println("!!!!!! Dosn't find ANYTHING   !!!!");
        }
        if (cookingOrder == null) {
            // Create a new CookingOrder if it doesn't exist
            cookingOrder = new CookingOrder();
            cookingOrder.setOrderId(order.getId());
            String json = JsonConversionUtil.convertListToJson(order.getOrderItems());
            cookingOrder.setMenuItems(json);
            cookingOrder.setTotalCookingTimeSec(order.getTotalCookingTimeSec());
            cookingOrder.setOrderComments(order.getOrderComments());
            cookingOrder.setOrderDate(order.getCreatedAt());
            cookingOrder.setDelivery(order.isDelivery());
            cookingOrder.setDeliveryAddress(order.getDeliveryAddress());
            cookingOrder.setDeliveryTime(order.getDeliveryTime());
            cookingOrder.setStatus(order.getStatus());
            cookingOrder.setCustomerID(order.getCustomer().getId());
            return;
        } else {
            // Update CookingOrder if it already exists
            // Update properties if needed
        }

        // Save or update the CookingOrder in the database
        cookingOrderRepository.save(cookingOrder);
    }

    public class JsonConversionUtil {

        public static String convertListToJson(List<OrderItem> orderItemList) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Convert the List<OrderItem> to a JSON string
                return objectMapper.writeValueAsString(orderItemList);
            } catch (JsonProcessingException e) {
                // Handle JSON processing exception
                e.printStackTrace();
                return null; // Or handle the exception as required
            }
        }
    }

    @Override
    public Optional<CookingOrder> createCookingOrder(CookingOrder cookingOrder) {
        try {
            CookingOrder savedCookingOrder = cookingOrderRepository.save(cookingOrder);
            return Optional.ofNullable(savedCookingOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteCookingOrder(Long id) {
        try {
            cookingOrderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
