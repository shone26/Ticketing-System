import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private List<Ticket> ticketList;
    private int max_ticket_capacity;
    private int ticketIdGenerator;
    public int totalInitialTickets;


    public TicketPool(int max_ticket_capacity, int totalInitialTickets) {
        this.ticketList = new ArrayList<Ticket>();
        this.max_ticket_capacity = max_ticket_capacity;
        this.totalInitialTickets = totalInitialTickets;
        this.ticketIdGenerator = 1;
    }

    public synchronized void addTicket(int ticketAmount, double ticketPrice, String eventName, String vendorName) {
        while(ticketList.size() + ticketAmount > max_ticket_capacity) {
            try {
                System.out.println("Ticket pool is full. Wait...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (int i = 0; i < ticketAmount; i++) {
            Ticket ticket = new Ticket(ticketIdGenerator++, eventName, ticketPrice);
            ticketList.add(ticket);
            System.out.println("A ticket added by vendor " + vendorName + ". Ticket details " + ticket );
            notifyAll();
        }

    }

    public synchronized boolean removeTicket(int retrevalRate,String customerName) {
        while(ticketList.size() - retrevalRate < 0) {
            try {
                System.out.println("Ticket pool is empty. Wait...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (int i = 0; i < retrevalRate; i++) {
            Ticket ticket = ticketList.remove(0);
            ticket.purchaseTicket(customerName);
            System.out.println("Ticket purchased by " + customerName + ". Ticket details " + ticket );
        }
        return true;
    }

    public int getAvailableTickets() {
        return ticketList.size();
    }


}