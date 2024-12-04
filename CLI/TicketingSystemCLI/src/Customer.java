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
//        if (isVip==true) {
//            this.setPriority(Thread.MAX_PRIORITY);
//        }else{
//            this.setPriority(Thread.NORM_PRIORITY);
//        }
//        System.out.println(this.getPriority());
        while (true) {
            if (isVip) {
                purchaseTicket();  // VIP customers are prioritized
            } else {
                purchaseTicket();  // Regular customers can also purchase tickets
            }

        }
    }
    public void purchaseTicket() {
        if (ticketPurchased < 4) {
            if (isVip) {
                ticketPool.removeTicket(retrieveRate, customerName, ticketAmount);

            }else {
                ticketPool.removeTicket(retrieveRate, customerName, ticketAmount);
            }
            ticketPurchased++;
        }else {
            try{
                System.out.println(customerName+" has reached maximum limit of 4 tickets. Plz wait 10 seconds.");
                sleep(10000);
                ticketPurchased = 0;
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
