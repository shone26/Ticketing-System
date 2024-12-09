package lk.iit.ticketingsystem.Controllers;


import lk.iit.ticketingsystem.Database.CustomerRepository;
import lk.iit.ticketingsystem.Database.VendorRepository;
import lk.iit.ticketingsystem.Models.Configuration;
import lk.iit.ticketingsystem.Models.Customer;
import lk.iit.ticketingsystem.Models.Vendor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class VendorController {
    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {

        this.vendorRepository = vendorRepository;
    }

    @PostMapping("/add-vendor")
    public String addVendors(@RequestBody List<Vendor> vendors) {
        // Save the list of vendor objects to the repository
        vendorRepository.saveAll(vendors);
        return "Added " + vendors.size() + " vendors";
    }

}
