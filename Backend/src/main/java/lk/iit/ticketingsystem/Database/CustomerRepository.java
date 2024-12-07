package lk.iit.ticketingsystem.Database;

import lk.iit.ticketingsystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
