package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.model.Customer;

import java.util.Optional;

public interface CustomerServiceInt {
    Optional<Customer> findCustomerByEmailOrPhone(String email, String phone);
    Customer addCustomer(Customer customer);
}
