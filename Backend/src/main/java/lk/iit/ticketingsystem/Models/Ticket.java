package lk.iit.ticketingsystem.Models;

import jakarta.persistence.*;

@Entity
public class Ticket {
    private @Id @GeneratedValue int id;
    private String price;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerID")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendorID")
    private Vendor vendorId;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "sessionID")
    private Session sessionId;

    public Ticket() {}


    public Ticket(int id, String price, Customer customerId, Vendor vendorId, Session sessionId) {
        this.id = id;
        this.price = price;
        this.customerId = customerId;
        this.vendorId = vendorId;
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Vendor getVendorId() {
        return vendorId;
    }

    public void setVendorId(Vendor vendorId) {
        this.vendorId = vendorId;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }
}
