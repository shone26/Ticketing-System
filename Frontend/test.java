import java.math.BigDecimal;
public class Vendor implements Runnable{    
    private TicketPool ticketPool; // The TicketPool which is shared among Vendors and Customers    
    private int totalTickets; // Tickets vendor will to sell    
    private int ticketReleaseRate; // Frequency tickets will be added to pool    
    
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
                this.ticketPool = ticketPool;        
                this.totalTickets = totalTickets;        
                this.ticketReleaseRate = ticketReleaseRate;    }    // Implement the thread    // Runnable interface should write the implementation for Runnable interface    
                @Override    
                public void run() {
                            for (int i = 1; i <= totalTickets; i++) {
                                            // i is used as ID            
                                            Ticket ticket = new Ticket(i, "Event Simple", new BigDecimal("1000"));            
                                            ticketPool.addTickets(ticket); // Method in Ticket Pool to add tickets            // The ticket release frequency means the delay            // We should convert the value in S to Ms            
                                            try {
                                                    Thread.sleep(ticketReleaseRate * 1000);            
                                                    } 
                                                    catch (InterruptedException e) {
                                                                        throw new RuntimeException(e.getMessage());            
                                                                        }        }
                                                                            
                                                                            }
                                                                            }