package lk.iit.ticketingsystem.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lk.iit.ticketingsystem.Controllers.VendorController;

import java.util.List;

@Entity
public class Vendor {
    private @Id @GeneratedValue int vendorId;
    private String firstName;
    private String lastName;
    private int releaseTicketAmount;

    @OneToMany(mappedBy = "vendor")
    private List<Ticket> tickets;

    public Vendor() {}

    public Vendor(String firstName, String lastName, int releaseTicketAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.releaseTicketAmount = releaseTicketAmount;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
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

    public int getReleaseTicketAmount() {
        return releaseTicketAmount;
    }

    public void setReleaseTicketAmount(int releaseTicketAmount) {
        this.releaseTicketAmount = releaseTicketAmount;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
