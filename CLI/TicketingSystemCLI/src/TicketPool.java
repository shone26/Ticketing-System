import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketPool {
    private List<Ticket> ticketList;
    private int max_ticket_capacity;
    private int ticketIdGenerator;
    private int totalInitialTickets;
    private int currentTicketId;
    private List<Double> priceList = new ArrayList<>(List.of(2000.00, 4000.00));




    public TicketPool(int max_ticket_capacity, int totalInitialTickets) {
        this.ticketList = new ArrayList<Ticket>();
        this.max_ticket_capacity = max_ticket_capacity;
        this.totalInitialTickets = totalInitialTickets;
        this.ticketIdGenerator = 1;
        this.currentTicketId = totalInitialTickets;

        for (int i = 0; i < totalInitialTickets; i++) {
            ticketList.add(new Ticket(ticketIdGenerator++, getRandomPrice()));
        }
    }

    private double getRandomPrice() {
        Random random = new Random();
        int randomIndex = random.nextInt(priceList.size());
        return priceList.get(randomIndex);
    }


    public synchronized void addTicket(int releaseRate, int totalTickets, double ticketPrice, String vendorName, int releaseTicketAmount) {

        if (totalInitialTickets >= max_ticket_capacity) {
            try {

//                System.out.println(ticketList.size()+totalTickets);
//                System.out.println(ticketList.size());
//                System.out.println(max_ticket_capacity);
                System.out.println("Ticket pool is full. Wait...");
                wait();
            } catch (InterruptedException ignored) {
            }
        }else {
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

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

}