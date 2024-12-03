public class Vendor implements Runnable {
    public TicketPool ticketPool;
    private String eventName;
    private int releaseRate;
    private double price;

    public Vendor(TicketPool ticketPool, String eventName, int releaseRate, double price) {
        this.ticketPool = ticketPool;
        this.eventName = eventName;
        this.releaseRate = releaseRate;
        this.price = price;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //12 per minute
                ticketPool.addTicket(name);
                Thread.sleep(7500);

            } catch (InterruptedException e) {
                System.out.println(name + " stopped adding interrupted");
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}
