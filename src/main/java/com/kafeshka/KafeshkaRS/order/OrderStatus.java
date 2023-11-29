package com.kafeshka.KafeshkaRS.order;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    ORDER_QUEUED("The order placed and queued"),
    IN_PROGRESS("The order is in progress"),
    COOKING("The order is still cooking"),
    PREPARING("The order in preparing status"),
    COOLING("The order is in cooling status"),
    REHEATING("The order is reheating"),
    ON_THEWAY("The order is on the to the customer"),
    SERVING("The order is serving to customer"),
    DELIVERED("The order was delivered"),
    CANCELED("The order was canceled");

    private final String StatusDesc;

    OrderStatus(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public static List<OrderStatus> getOrdersInProgress() {
        return Arrays.asList(
                OrderStatus.IN_PROGRESS,
                OrderStatus.REHEATING,
                OrderStatus.COOLING,
                OrderStatus.PREPARING,
                OrderStatus.COOKING,
                OrderStatus.ORDER_QUEUED
        );
    }
}
