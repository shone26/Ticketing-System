public class Vendor implements Runnable {
    public TicketPool ticketPool;
    private int releaseRate;
    private double price;
    private String vendorName;

    private int releaseTicketAmount;

    public Vendor(TicketPool ticketPool,  int releaseRate, String vendorName, double price, int releaseTicketAmount) {
        this.ticketPool = ticketPool;

        this.releaseRate = releaseRate;
        this.vendorName = vendorName;
        this.price = price;
        this.releaseTicketAmount = releaseTicketAmount;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.addTicket(releaseRate, price, vendorName, releaseTicketAmount);
            try {
                //12 per minute
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}
