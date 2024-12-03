public class Vendor implements Runnable {
    public final TicketPool ticketPool;
    private final String name;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, String name, int releaseRate) {
        this.ticketPool = ticketPool;
        this.name = name;
        this.releaseRate = releaseRate;
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
