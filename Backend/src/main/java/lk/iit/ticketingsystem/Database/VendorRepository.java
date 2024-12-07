package lk.iit.ticketingsystem.Database;

import lk.iit.ticketingsystem.Models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
