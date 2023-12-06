package com.kafeshka.KafeshkaRS.web.controllers.api;

import com.kafeshka.KafeshkaRS.model.Order;
import com.kafeshka.KafeshkaRS.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    private final OrderService orderService;

    @Autowired
    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        Optional<Order> createdOrder = orderService.createOrder(order);

        if (createdOrder.isPresent()) {
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        boolean deleted = orderService.deleteOrder(orderId);

        if (deleted) {
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete order", HttpStatus.NOT_FOUND);
        }
    }
}

