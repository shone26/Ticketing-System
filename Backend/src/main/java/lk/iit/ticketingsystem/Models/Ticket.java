package lk.iit.ticketingsystem.Models;

import jakarta.persistence.*;

@Entity
public class Ticket {
    private @Id @GeneratedValue int ticketId;
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private String purchaserName;

//    @ManyToOne
//    @JoinColumn(name = "session_id")
//    private Session session;

    private boolean isPurchased;

    public Ticket() {}


    public Ticket(int ticketId, double ticketPrice) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.isPurchased = false;
        this.purchaserName = null;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    // Method to purchase the ticket
    public void purchaseTicket(String customerName) {
        this.isPurchased = true;
        this.purchaserName = customerName;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", Price: " + ticketPrice + ", Purchased: " + isPurchased;
    }
}
