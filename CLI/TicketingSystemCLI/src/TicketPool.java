import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private List<Ticket> ticketList;
    private int max_ticket_capacity;
    private int ticketIdGenerator;


    public TicketPool(int max_ticket_capacity) {
        this.ticketList = new ArrayList<Ticket>();
        this.max_ticket_capacity = max_ticket_capacity;
        this.ticketIdGenerator = 1;
    }

    public synchronized void addTicket(int ticketAmount, double ticketPrice, String eventName) {
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
            notifyAll();
        }

    }

    public synchronized boolean removeTicket(String customerName) {
        while(ticketList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket ticket = ticketList.remove(0);
        ticket.purchaseTicket(customerName);
        System.out.println("Ticket purchased by " + customerName + ". Ticket details " + ticket );
        return true;
    }

    public int getAvailableTickets() {
        return ticketList.size();
    }


}