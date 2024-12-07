package lk.iit.ticketingsystem.Database;

import lk.iit.ticketingsystem.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}
