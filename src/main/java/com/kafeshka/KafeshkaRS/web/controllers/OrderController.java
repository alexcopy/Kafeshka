package com.kafeshka.KafeshkaRS.web.controllers;

import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.order.OrderStatus;
import com.kafeshka.KafeshkaRS.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/createOrder")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "create_order"; // This will reference the create_order.html template
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") Order order) {
        order.setStatus(OrderStatus.ORDER_QUEUED);
        int totalCookingTimeSec = (order.getOrderItems().size() * 120) + 120;
        order.setTotalCookingTimeSec(totalCookingTimeSec);
        if (order.isDelivery()) {
            order.setDeliveryTime(new Date());
        }
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @GetMapping()
    public String showOrders(Model model) {
        // Fetch list of orders from the OrderService
        Optional<List<Order>> orders = orderService.getAllOrders();
        // Add the list of orders to the model attribute
        model.addAttribute("orders", orders);
        // Return the Thymeleaf template name (orders.html)
        return "orders";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);

        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Optional<Order> createdOrder = orderService.createOrder(order);

        return createdOrder.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Order updated = orderService.updateOrder(id, updatedOrder);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
