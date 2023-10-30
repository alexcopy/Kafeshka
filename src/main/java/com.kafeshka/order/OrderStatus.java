package com.kafeshka.order;

public enum OrderStatus {
    IN_PROGRESS("The order is still cooking"),
    ON_THEWAY("The order is on the to the customer"),
     DELIVERED("The order was delivered"),
    CANCELED("The order was canceled");

    private String StatusDesc;

    OrderStatus(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }
}
