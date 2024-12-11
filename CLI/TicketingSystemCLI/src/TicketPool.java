import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class TicketPool {
    // A collection to store tickets available in the pool
    private List<Ticket> ticketList;
    private int max_ticket_capacity;
    private int ticketIdGenerator;
    private List<Double> priceList = new ArrayList<>(List.of(2000.00, 4000.00));

    // Lock for advanced synchronization
    private final Lock lock = new ReentrantLock();
    private final Condition ticketAvailableCondition = lock.newCondition();
    private final Condition ticketSpaceCondition = lock.newCondition();

    //To colorize the CLI output
    public static final String RESET = "\u001B[0m"; // Reset color
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";


    // Constructor where I fill the pool with the given number of tickets
    public TicketPool(int max_ticket_capacity, int totalInitialTickets) {
        this.ticketList = new ArrayList<Ticket>();
        this.max_ticket_capacity = max_ticket_capacity;
        this.ticketIdGenerator = 1;


        for (int i = 0; i < totalInitialTickets; i++) {
            ticketList.add(new Ticket(ticketIdGenerator++, getRandomPrice()));
        }
    }

    private double getRandomPrice() {
        Random random = new Random();
        int randomIndex = random.nextInt(priceList.size());
        return priceList.get(randomIndex);
    }

    // Add ticket with ReentrantLock
    // Method where I add tickets to the pool at the rate specified.
    //addTicket() is used by vendors to add tickets to the pool at a specified rate.
    public void addTicket(int releaseRate, double ticketPrice, String vendorName, int releaseTicketAmount) {

        lock.lock(); // Acquire the lock
        try {
            for (int i = 0; i < releaseTicketAmount; i++) {
                if (ticketIdGenerator > max_ticket_capacity) {
                    try {
                        System.out.println("The ticket pool is full");
                        ticketSpaceCondition.await(); // Wait for space to be available
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    Ticket ticket = new Ticket(ticketIdGenerator++, ticketPrice);
                    ticketList.add(ticket);
                    Thread.sleep(60000 / releaseRate);
                    System.out.println(GREEN + "A ticket added by vendor " + vendorName + ". Ticket ID " + ticket.getTicketId() + ". Remaining Tickets: " + ticketList.size() + RESET);

                    ticketAvailableCondition.signalAll(); // Notify customers that tickets are available
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    // Method where I remove tickets from the pool when a customer buys them.
    // Remove ticket with ReentrantLock
    public boolean removeTicket(int retrievalRate, String customerName, int ticketAmount) {
        lock.lock(); // Acquire the lock
        try {
            while (ticketList.isEmpty()) {
                try {
                    System.out.println("All the tickets are sold...");
                    ticketAvailableCondition.await(); // Wait for tickets to be available
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }

            if (ticketList.size() >= ticketAmount) {
                for (int i = 0; i < ticketAmount; i++) {
                    try {
                        Ticket ticket = ticketList.remove(0);
                        ticket.purchaseTicket(customerName);
                        Thread.sleep(60000 / retrievalRate); // Simulate retrieval delay
                        System.out.println(RED + "Ticket purchased by " + customerName + ". Ticket ID " + ticket.getTicketId() +  " Remaining Tickets: " + ticketList.size() + RESET);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
                return true;
            } else {
                try {
                    System.out.println("Not enough tickets available.");
                    ticketSpaceCondition.await(); // Wait until tickets are added by vendors
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }

}
