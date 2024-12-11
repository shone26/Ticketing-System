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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StartController {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    @PostMapping("/start")
//    public String start() {
//        System.out.println("System has started");
//
//        // Initialize the TicketPoolService (this can be injected as a bean if needed)
//        TicketPoolService ticketPoolService = new TicketPoolService(30, 20);
//
//        // Create vendor and customer threads
//        Thread vendor1 = new Thread(new Vendor(ticketPoolService, 50, "Shone", 2000, 7));
//        Thread vendor2 = new Thread(new Vendor(ticketPoolService, 50, "Dasun", 4000, 5));
//
//        Thread customer1 = new Thread(new Customer(ticketPoolService, "Shanaka", 50, 3));
//        Thread customer2 = new Thread(new Customer(ticketPoolService, "Namidu", 50, 5));
//        Thread customer3 = new Thread(new Customer(ticketPoolService, "Umesh", 50, 4));
//        Thread customer4 = new Thread(new Customer(ticketPoolService, "Athif", 50, 1));
//
//        // Start threads
//        vendor1.start();
//        vendor2.start();
//        customer1.start();
//        customer2.start();
//        customer3.start();
//        customer4.start();
//
//        // Optionally, wait for threads to finish using join() if you want to block the main thread
//        try {
//            vendor1.join();
//            vendor2.join();
//            customer1.join();
//            customer2.join();
//            customer3.join();
//            customer4.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return "start"; // Or return a redirect if needed
//    }


//
//    @PostMapping("startsystem")
//    public String startsystem() {
//        System.out.println("System has started");
//        TicketPoolService ticketPoolService = new TicketPoolService(30, 20);
//
//        List<lk.iit.ticketingsystem.Models.Vendor> vendors = vendorRepository.findAll();
//        List<lk.iit.ticketingsystem.Models.Customer> customers = customerRepository.findAll();
//
//
//        for (lk.iit.ticketingsystem.Models.Vendor vendor : vendors) {
//            Thread vendorThread = new Thread(new Vendor(ticketPoolService, 50, vendor.getFirstName(), 2000.0, vendor.getReleaseTicketAmount()));
//            vendorThread.start();
//
//        }
//
//        for (lk.iit.ticketingsystem.Models.Customer customer : customers) {
//            Thread customerThread = new Thread(new Customer(ticketPoolService, customer.getFirstName(), 50, customer.getRetrieveTicketAmount()));
//            customerThread.start();
//
//        }
//
//        return "startsystem";
//    }

    @PostMapping("/startsystem")
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
