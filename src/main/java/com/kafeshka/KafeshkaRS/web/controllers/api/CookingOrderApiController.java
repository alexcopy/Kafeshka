package com.kafeshka.KafeshkaRS.web.controllers.api;

import com.kafeshka.KafeshkaRS.cook_manager.model.CookingOrder;
import com.kafeshka.KafeshkaRS.cook_manager.service.CookingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cooking-orders")
public class CookingOrderApiController {

    private final CookingOrderService cookingOrderService;

    @Autowired
    public CookingOrderApiController(CookingOrderService cookingOrderService) {
        this.cookingOrderService = cookingOrderService;
    }

    @PostMapping
    public ResponseEntity<String> createCookingOrder(@RequestBody CookingOrder cookingOrder) {
        Optional<CookingOrder> createdCookingOrder = cookingOrderService.createCookingOrder(cookingOrder);

        return createdCookingOrder.map(value -> new ResponseEntity<>("Cooking order created successfully", HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>("Failed to create cooking order", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCookingOrder(@PathVariable Long id) {
        boolean deleted = cookingOrderService.deleteCookingOrder(id);

        if (deleted) {
            return new ResponseEntity<>("Cooking order deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete cooking order", HttpStatus.NOT_FOUND);
        }
    }

}

