public class Ticket {
    private int ticketId;
    private String customerName;
    private String eventName;
    private double ticketPrice;
    private boolean isPurchased;

    public Ticket(int ticketId, String eventName, double ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.isPurchased = false;
    }
    public int getTicketId() {
        return ticketId;
    }
    public String getEventName() {
        return eventName;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    public boolean isPurchased() {
        return isPurchased;
    }

    public void purchaseTicket(String customerName) {
        this.customerName=customerName;
        this.isPurchased = true;

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", customerName='" + (customerName != null ? customerName : "Not Purchased") + '\'' +
                ", price=" + ticketPrice +
                ", isPurchased=" + isPurchased +
                '}';
    }

}
