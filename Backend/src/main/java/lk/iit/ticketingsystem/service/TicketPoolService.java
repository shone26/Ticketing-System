package lk.iit.ticketingsystem.service;


import lk.iit.ticketingsystem.Models.Ticket;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class TicketPoolService {

    // Synchronized list for thread-safe access
    private final List<Ticket> ticketList;

    // Injecting maxTicketCapacity using @Value
    @Value("${ticket.max-capacity}")
    private int maxTicketCapacity;

    // Ticket ID generator
    private int ticketIdGenerator;

    // List of possible ticket prices
    private final List<Double> priceList;

    public TicketPoolService(int maxTicketCapacity, @Value("${ticket.initial-tickets}") int totalInitialTickets) {
        this.ticketList = Collections.synchronizedList(new ArrayList<>());
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketIdGenerator = 1;
        this.priceList = List.of(2000.00, 4000.00);  // Example prices

        // Initialize with some tickets
        for (int i = 0; i < totalInitialTickets; i++) {
            ticketList.add(new Ticket(ticketIdGenerator++, getRandomPrice()));
        }
    }

    // Method to generate a random ticket price from priceList
    private double getRandomPrice() {
        Random random = new Random();
        return priceList.get(random.nextInt(priceList.size()));
    }

    // Add tickets to the pool (by vendors)
    public synchronized void addTickets(int releaseRate, double ticketPrice, String vendorName, int releaseTicketAmount) {
        for (int i = 0; i < releaseTicketAmount; i++) {
            if (ticketIdGenerator > maxTicketCapacity) {
                try {
                    System.out.println("The ticket pool is full.");
                    wait();  // Wait if the pool is full
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                Ticket ticket = new Ticket(ticketIdGenerator++, ticketPrice);
                ticketList.add(ticket);
                Thread.sleep(60000 / releaseRate);  // Simulate rate delay
                System.out.println("A ticket added by vendor " + vendorName + ". Ticket details: " + ticket);
                notifyAll();  // Notify other threads when a ticket is added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    // Method to purchase tickets (by customers)
    public synchronized boolean removeTicket(int retrievalRate, String customerName, int ticketAmount) {
        while (ticketList.isEmpty()) {
            try {
                System.out.println("All tickets are sold...");
                wait();  // Wait if there are no tickets available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        // Only proceed if there are enough tickets available
        if (ticketList.size() >= ticketAmount) {
            for (int i = 0; i < ticketAmount; i++) {
                try {
                    Ticket ticket = ticketList.remove(0);  // Remove the first ticket from the list
                    ticket.purchaseTicket(customerName);
                    Thread.sleep(60000 / retrievalRate);  // Simulate retrieval delay
                    System.out.println("Ticket purchased by " + customerName + ". Ticket details: " + ticket);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
            return true;  // Successfully removed tickets
        } else {
            System.out.println("Not enough tickets available.");
            try {
                wait();  // Wait if there are not enough tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return false;  // Not enough tickets to fulfill the request
        }
    }

    // Get the current available tickets
    public int getAvailableTickets() {
        return ticketList.size();
    }
}