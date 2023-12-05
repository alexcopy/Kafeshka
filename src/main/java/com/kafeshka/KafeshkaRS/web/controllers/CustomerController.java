package com.kafeshka.KafeshkaRS.web.controllers;

import com.kafeshka.KafeshkaRS.model.Customer;
import com.kafeshka.KafeshkaRS.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    // Constructor injection of CustomerService
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer()); // Empty customer object for the form
        return "add_customer"; // Thymeleaf template name (add_customer.html)
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {

            return "add_customer";
        }
        customerService.addCustomer(customer); // Save the new customer using the service
        return "redirect:/customers"; // Redirect to the list of customers page
    }
    @GetMapping("/customers")
    public String showAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "all_customers"; // Thymeleaf template name (all_customers.html)
    }
}
