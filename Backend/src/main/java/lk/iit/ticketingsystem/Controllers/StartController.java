package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.Database.CustomerRepository;
import lk.iit.ticketingsystem.Database.VendorRepository;
import lk.iit.ticketingsystem.Models.Configuration;
import lk.iit.ticketingsystem.Models.threading.CustomerThread;
import lk.iit.ticketingsystem.Models.threading.VendorThread;
import lk.iit.ticketingsystem.service.JsonReader;
import lk.iit.ticketingsystem.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/system")
public class StartController {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/start")
    public String startsystem() {
        System.out.println("System has started");


        Configuration config = null;
        try {
            config = JsonReader.readConfigFromFile("config.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialize the TicketPoolService (you can inject it as a bean if needed)
        TicketPoolService ticketPoolService = new TicketPoolService(config.getMaximumTicketCapacity(), config.getTotalTickets());

        // Fetch all vendors and customers from the database dynamically
        List<lk.iit.ticketingsystem.Models.Vendor> vendors = vendorRepository.findAll();
        List<lk.iit.ticketingsystem.Models.Customer> customers = customerRepository.findAll();

        // List to store vendor and customer threads
        List<Thread> vendorThreads = new ArrayList<>();
        List<Thread> customerThreads = new ArrayList<>();

        // Create and start vendor threads
        for (lk.iit.ticketingsystem.Models.Vendor vendor : vendors) {
            Thread vendorThread = new Thread(new VendorThread(ticketPoolService, config.getTicketReleaseRate(), vendor.getFirstName(), 2000.0, vendor.getReleaseTicketAmount()));
            vendorThreads.add(vendorThread);
            vendorThread.start();  // Start the vendor thread
        }

        // Create and start customer threads
        for (lk.iit.ticketingsystem.Models.Customer customer : customers) {
            Thread customerThread = new Thread(new CustomerThread(ticketPoolService, customer.getFirstName(), config.getTicketRetrievalRate(), customer.getRetrieveTicketAmount()));
            System.out.println(customer.getRetrieveTicketAmount());
            customerThreads.add(customerThread);
            customerThread.start();  // Start the customer thread
        }

        // Wait for all vendor threads to finish
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();  // Wait for vendor thread to finish
            }


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Wait for all customer threads to finish
        try {
            for (Thread customerThread : customerThreads) {
                customerThread.join();  // Wait for customer thread to finish
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "start"; // Or redirect if needed
    }





}
