package lk.iit.ticketingsystem.Database;

import lk.iit.ticketingsystem.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
//    List<Ticket> findTopNByPurchasedFalse(int ticketAmount);
}

