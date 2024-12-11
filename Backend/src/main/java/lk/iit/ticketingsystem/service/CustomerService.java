//package lk.iit.ticketingsystem.service;
//
//import lk.iit.ticketingsystem.Database.CustomerRepository;
//import lk.iit.ticketingsystem.Models.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomerService {
//
////    @Autowired
////    private TicketRepository ticketRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;  // Assuming Customer repository
//
//    public void addNewCustomer(Customer customer) {
//        // Automatically delete all previous customers before saving the new customer
//        customerRepository.deleteAll(); // This deletes all the customers from the database
//
//        // Now add the new customer
//        customerRepository.save(customer);
//    }
//}
