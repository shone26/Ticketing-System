package lk.iit.ticketingsystem.Database;

import lk.iit.ticketingsystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}


