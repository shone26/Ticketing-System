import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int availableTickets;
    private int max_ticket_capacity;
    private Lock lock = new ReentrantLock() ;

    public TicketPool(int availableTickets, int max_ticket_capacity) {
        this.availableTickets = availableTickets;
        this.max_ticket_capacity = max_ticket_capacity;
    }

    public synchronized void buyTicket(String customerName) {

        while (availableTickets == 0) {
            System.out.println(customerName + " is waiting for a ticket to buy...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        availableTickets--;
        System.out.println(customerName + " has bought a ticket. Remaining: " + availableTickets);
        notifyAll();
    }

    public synchronized void addTicket(String vendorName) {

        while (availableTickets >= max_ticket_capacity) {

            try {
                System.out.println(vendorName + " is waiting for a ticket to add... pool is full");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        availableTickets++;
        System.out.println(vendorName + " has added a ticket. Available tickets: " + availableTickets);
        notifyAll();
    }


}