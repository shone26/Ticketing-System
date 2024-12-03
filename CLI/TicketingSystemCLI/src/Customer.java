public class Customer implements Runnable{
    public TicketPool ticketPool;
    private String customerName;
    private int retrieveRate;

    public Customer(TicketPool ticketPool, String customerName, int retrieveRate) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrieveRate = retrieveRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ticketPool.removeTicket(retrieveRate, customerName);
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
