// The Ticket class represents an individual ticket in the ticketing system.
// Each ticket will have a unique ID and a fixed price.

public class Ticket {
    // Static variable ticketCounter to generate unique ticket IDs.
    private int ticketId;
    private String customerName;
    private double ticketPrice;
    private boolean isPurchased;

    // Constructor where I initialize the system with a set number of tickets and the ids
    public Ticket(int ticketId, double ticketPrice) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.isPurchased = false;
    }
    public int getTicketId() {
        return ticketId;
    }


    public void purchaseTicket(String customerName) {
        this.customerName=customerName;
        this.isPurchased = true;

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", customerName='" + (customerName != null ? customerName : "Not Purchased") + '\'' +
                ", price=" + ticketPrice +
                ", isPurchased=" + isPurchased +
                '}';
    }

}
