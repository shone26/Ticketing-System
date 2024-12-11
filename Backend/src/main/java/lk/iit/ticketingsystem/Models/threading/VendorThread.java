package lk.iit.ticketingsystem.Models.threading;

import lk.iit.ticketingsystem.service.TicketPoolService;
import org.springframework.stereotype.Component;

@Component
public class VendorThread implements Runnable {
    private TicketPoolService ticketPoolService;
    private int releaseRate;
    private double price;
    private String vendorName;
    private int releaseTicketAmount;

    public VendorThread(){}

    public VendorThread(TicketPoolService ticketPoolService, int releaseRate, String vendorName, double price, int totalTicketAmount) {
        this.ticketPoolService = ticketPoolService;
        this.releaseRate = releaseRate;
        this.vendorName = vendorName;
        this.price = price;
        this.releaseTicketAmount = totalTicketAmount;

    }

    @Override
    public void run() {
        while (true) {

            ticketPoolService.addTickets(releaseRate, price, vendorName, releaseTicketAmount);
            try {
                // Release tickets every 2 seconds (simulate the process)
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
