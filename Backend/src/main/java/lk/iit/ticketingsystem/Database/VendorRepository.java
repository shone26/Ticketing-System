package lk.iit.ticketingsystem.Database;

import lk.iit.ticketingsystem.Models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {


//    Vendor findByName(String vendorName);
//    @Query("SELECT v FROM Vendor v WHERE v.vendorId = :name")
//    Vendor findByName(@Param("name") String name);
}
