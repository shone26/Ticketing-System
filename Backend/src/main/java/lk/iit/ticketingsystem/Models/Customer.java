package lk.iit.ticketingsystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jdk.jfr.BooleanFlag;

import java.util.List;

@Entity
public class Customer {
    private @Id @GeneratedValue int customerId;
    private String firstName;
    private String lastName;
    private int retrieveTicketAmount;


    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    public Customer() {}

    public Customer(String firstName, String lastName, int retrieveTicketAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.retrieveTicketAmount = retrieveTicketAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRetrieveTicketAmount() {
        return retrieveTicketAmount;
    }

    public void setRetrieveTicketAmount(int retrieveTicketAmount) {
        this.retrieveTicketAmount = retrieveTicketAmount;
    }



    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
