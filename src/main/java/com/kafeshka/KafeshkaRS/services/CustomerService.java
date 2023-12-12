package com.kafeshka.KafeshkaRS.services;

import com.kafeshka.KafeshkaRS.model.Customer;
import com.kafeshka.KafeshkaRS.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInt {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void updateCustomer(Customer customer) {
        // TODO Perform any necessary checks/validation before updating
        customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findCustomerByEmailOrPhone(String email, String phone) {
        // Check if customer exists by email or phone
        Optional<Customer> existingCustomerByEmail = customerRepository.findByEmail(email);
        if (existingCustomerByEmail.isPresent()) {
            return existingCustomerByEmail;
        }

        Optional<Customer> existingCustomerByPhone = customerRepository.findByPhoneNumber(phone);
        return existingCustomerByPhone;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        // Implement logic to add a new customer to the database
        return customerRepository.save(customer);
    }
}

