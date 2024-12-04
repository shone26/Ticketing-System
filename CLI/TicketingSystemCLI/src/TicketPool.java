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

    public synchronized void addTicket(int releaseRate, int totalTickets, double ticketPrice, String vendorName, int releaseTicketAmount) {
        while(ticketList.size() + releaseTicketAmount + totalTickets > max_ticket_capacity) {
            try {
                System.out.println("Ticket pool is full. Wait...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (int i = 0; i < releaseTicketAmount; i++) {
            try {
                Ticket ticket = new Ticket(ticketIdGenerator++, ticketPrice);
                ticketList.add(ticket);
                Thread.sleep(60000/releaseRate);
                System.out.println("A ticket added by vendor " + vendorName + ". Ticket details " + ticket );
                notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public synchronized boolean removeTicket(int retrevalRate,String customerName, int ticketAmount) {
        while(ticketList.size() - ticketAmount < 0) {
            try {
                System.out.println("Ticket pool is empty. Wait...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (int i = 0; i < ticketAmount; i++) {
            try {
                Ticket ticket = ticketList.remove(0);
                ticket.purchaseTicket(customerName);
                Thread.sleep(60000/retrevalRate);
                System.out.println("Ticket purchased by " + customerName + ". Ticket details " + ticket );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public int getAvailableTickets() {
        return ticketList.size();
    }


}