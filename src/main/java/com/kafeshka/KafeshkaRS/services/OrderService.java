package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.model.Customer;
import com.kafeshka.KafeshkaRS.model.MenuItem;
import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.model.OrderItem;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInt {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final MenuItemServiceImpl menuItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, MenuItemServiceImpl menuItemService) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.menuItemService = menuItemService;
    }

    @Override
    public Optional<Order> createOrder(Order order) {
        System.out.println("-----------------START----------------------");
        System.out.println(order.getOrderItems());
        System.out.println("------------------END-----------------------");
        concatOrderItemsWithMenuItems(order.getOrderItems());
        try {
            addCustomerIfNotExists(order);
            order.setStatus(OrderStatus.ORDER_QUEUED);
            Order savedOrder = orderRepository.save(order);
            return Optional.ofNullable(savedOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    private void addMenuItemsIfNotExists(Order order){
//        List<OrderItem> orderItems = order.getOrderItems();
//
//
//        List<MenuItem> newOrderItems = new ArrayList<>();
//        for (OrderItem orderItem : orderItems) {
//           List<MenuItem> menuItems = new ArrayList<>();
//
//
//
//            if (menuItemService.getMenuItemByName(itemName).isEmpty()) {
//                // OrderItem doesn't exist, add it to the list of new items
//                newOrderItems.add(orderItem);
//            }
//        }
//
//        // Save the new OrderItems (if any) to the database
//        if (!newOrderItems.isEmpty()) {
//            orderItemRepository.saveAll(newOrderItems);
//        }
    }

    private List<OrderItem> concatOrderItemsWithMenuItems(List<OrderItem> orderItemList){
         List<OrderItem> checkedOrderItems = new ArrayList<>();

        List<MenuItem> menuItemIds = orderItemList.stream()
                .map(OrderItem::getMenuItem) // Extract menuItem IDs from OrderItems
                .toList();
        System.out.println("------------------Menu ITEMS-----------------------");
        System.out.println(menuItemIds);
        System.out.println("------------------END-----------------------");
//        Optional<List<MenuItem>> menuItemOptional = menuItemService.findItemsByIds(menuItemIds);

        return orderItemList;
    }

    private void addCustomerIfNotExists(Order order) {
        Customer customer = order.getCustomer();
        if (customer != null) {
            // Check if customer exists based on email or phone number
            Optional<Customer> existingCustomer = customerService.findCustomerByEmailOrPhone(customer.getEmail(), customer.getPhoneNumber());

            if (existingCustomer.isPresent()) {
                // Customer exists, use the existing customer
                order.setCustomer(existingCustomer.get());
            } else {
                // Customer doesn't exist, add the new customer
                Customer newCustomer = customerService.addCustomer(customer);
                order.setCustomer(newCustomer);
            }
        }
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        // Implement any validation or specific logic before updating
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }
        return null; // Or throw an exception if needed
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Optional<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        // Return an Optional of the orders list
        return Optional.of(orders);
    }

    @Override
    public Optional<List<Order>> getOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findByStatus(status);
        return Optional.ofNullable(orders.isEmpty() ? null : orders);
    }
}

