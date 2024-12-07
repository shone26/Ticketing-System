package lk.iit.ticketingsystem.Controllers;


import lk.iit.ticketingsystem.Database.CustomerRepository;
import lk.iit.ticketingsystem.Models.Configuration;
import lk.iit.ticketingsystem.Models.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add-customer")
    public String addPerson(@RequestBody Customer customer) {
        // Save the person object to the repository
        customerRepository.save(customer);
        return "Added " + customer.getFirstName();
    }

}
