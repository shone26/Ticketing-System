public class Customer extends Thread {
    public TicketPool ticketPool;
    private String customerName;
    private int retrieveRate;
    private boolean isVip;
    private int ticketPurchased;
    private int ticketAmount;

    public Customer(TicketPool ticketPool, String customerName, int retrieveRate, int ticketAmount, boolean isVip) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrieveRate = retrieveRate;
        this.ticketAmount = ticketAmount;
        this.isVip = isVip;
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



//            // Attempt to purchase a ticket (retrieval rate)
//            ticketPool.removeTicket(retrieveRate, customerName, ticketAmount);
//
//            try {
//                Thread.sleep(5000); // Simulate a 0.5-second interval between purchases
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();  // Handle interruption properly
//            }
        }
    }

}
