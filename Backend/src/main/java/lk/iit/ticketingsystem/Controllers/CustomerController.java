package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.Database.CustomerRepository;
import lk.iit.ticketingsystem.Models.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add-customer")
    public String addCustomer(@RequestBody List<Customer> customers) {
        // Delete all previous customers
        customerRepository.deleteAll();

        // Save the new customers to the repository
        customerRepository.saveAll(customers);

        return "Added " + customers.size() + " customers and deleted previous records.";
    }
    // Get method to retrieve all customers
    @GetMapping("/details")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();  // Fetches all customers from the database
    }
}
