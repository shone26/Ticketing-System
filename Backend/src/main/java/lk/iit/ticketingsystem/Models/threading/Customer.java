package lk.iit.ticketingsystem.Models.threading;

import lk.iit.ticketingsystem.service.TicketPoolService;
import org.springframework.stereotype.Component;


@Component
public class Customer implements Runnable {
    private TicketPoolService ticketPoolService;
    private String customerName;
    private int ticketRetrievalRate;
    private int ticketAmount;

    public Customer(){}

    public Customer(TicketPoolService ticketPoolService, String customerName, int ticketRetrievalRate, int ticketAmount) {
        this.ticketPoolService = ticketPoolService;
        this.customerName = customerName;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.ticketAmount = ticketAmount;
    }

    @Override
    public void run() {
        while (true) {
            if (ticketAmount <= 0) {
                System.out.println(customerName + " has retrieved all available tickets. Exiting...");
                break; // Exit the loop when no tickets are left
            }

            if (ticketAmount >= 4) {
                ticketPoolService.removeTicket(ticketRetrievalRate, customerName, 4); // Assume 4 tickets are retrieved
                ticketAmount -= 4; // Decrease the ticket amount by 4
                try {
                    System.out.println(customerName + " has bought 4 tickets in a row. Please wait 10 seconds...");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                ticketPoolService.removeTicket(ticketRetrievalRate, customerName, ticketAmount); // Retrieve the remaining tickets
                System.out.println(customerName + " has bought " + ticketAmount + " tickets.");
                ticketAmount = 0; // No tickets left
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }
}
