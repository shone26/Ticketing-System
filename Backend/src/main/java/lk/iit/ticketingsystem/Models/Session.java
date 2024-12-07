package lk.iit.ticketingsystem.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Session {
    private @Id @GeneratedValue int sessionId;
    private int totalTicketSold;

    public Session() {}

    public Session(int totalTicketSold) {
        this.totalTicketSold = totalTicketSold;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
