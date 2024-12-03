public class Vendor implements Runnable {
    public TicketPool ticketPool;
    private String eventName;
    private int releaseRate;
    private double price;
    private String vendorName;

    public Vendor(TicketPool ticketPool, String eventName, int releaseRate, String vendorName, double price) {
        this.ticketPool = ticketPool;
        this.eventName = eventName;
        this.releaseRate = releaseRate;
        this.vendorName = vendorName;
        this.price = price;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.addTicket(releaseRate, price, eventName, vendorName);
            try {
                //12 per minute
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
