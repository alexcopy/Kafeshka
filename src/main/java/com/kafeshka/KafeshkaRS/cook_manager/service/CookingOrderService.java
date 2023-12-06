package com.kafeshka.KafeshkaRS.cook_manager.service;

import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;
import com.kafeshka.KafeshkaRS.cook_manager.repository.CookingOrderRepo;
import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class CookingOrderService implements CookingOrderServiceInt {

    private final CookingOrderRepo cookingOrderRepository;

    @Autowired
    public CookingOrderService(CookingOrderRepo cookingOrderRepository) {
        this.cookingOrderRepository = cookingOrderRepository;
    }

    public void syncWithOrder(Order order) {
        // Create or update CookingOrder based on Order details
        CookingOrder cookingOrder = cookingOrderRepository.findByOrderId(order.getId());

        if (cookingOrder == null) {
            // Create a new CookingOrder if it doesn't exist
            cookingOrder = new CookingOrder();
            cookingOrder.setOrderId(order.getId());
            String json = JsonConversionUtil.convertListToJson(order.getOrderItems());
            cookingOrder.setMenuItems(json);
            cookingOrder.setTotalCookingTimeSec(order.getTotalCookingTimeSec());
            cookingOrder.setOrderComments(order.getOrderComments());
            cookingOrder.setOrderDate(order.getOrderDate());
            cookingOrder.setDelivery(order.isDelivery());
            cookingOrder.setDeliveryAddress(order.getDeliveryAddress());
            cookingOrder.setDeliveryTime(order.getDeliveryTime());
            cookingOrder.setStatus(order.getStatus());
            cookingOrder.setCustomerID(order.getCustomer().getId());
            cookingOrder.setOrderId(order.getId());
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
