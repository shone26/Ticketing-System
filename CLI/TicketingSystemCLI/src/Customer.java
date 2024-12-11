public class Customer extends Thread {
    public TicketPool ticketPool;
    private String customerName;
    private int retrieveRate;

    private int ticketAmount;

    public Customer(TicketPool ticketPool, String customerName, int retrieveRate, int ticketAmount) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrieveRate = retrieveRate;
        this.ticketAmount = ticketAmount;

    }


    @Override
    public void run() {
        while (true) {

            if (ticketAmount >= 4 ){
                ticketPool.removeTicket(retrieveRate, customerName, ticketAmount);
                try {
                    System.out.println(customerName + " has bought 4 tickets in row. Please wait 10 seconds...");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }else{
                ticketPool.removeTicket(retrieveRate, customerName, ticketAmount);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
