package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.Database.VendorRepository;
import lk.iit.ticketingsystem.Models.Vendor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostMapping("/add-vendor")
    public String addVendors(@RequestBody List<Vendor> vendors) {
        // Delete all previous vendor records
        vendorRepository.deleteAll();

        // Save the new list of vendors to the repository
        vendorRepository.saveAll(vendors);

        return "Added " + vendors.size() + " vendors and deleted previous records.";
    }
}
