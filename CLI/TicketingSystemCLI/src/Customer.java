public class Customer implements Runnable{
    public final TicketPool ticketPool;
    private final String name;
    private final int retrieveRate;

    public Customer(TicketPool ticketPool, String name, int retrieveRate) {
        this.ticketPool = ticketPool;
        this.name = name;
        this.retrieveRate = retrieveRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                 //6 tickets per minute
                ticketPool.buyTicket(name);
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                System.out.println(name + " stopped purchasing tickets...");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
