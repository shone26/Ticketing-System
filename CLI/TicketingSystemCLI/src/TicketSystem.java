import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



public class TicketSystem {
    public static void main(String[] args) throws InterruptedException {
        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;
        int maxTicketCapacity = 0;

        System.out.println(
                "*********************************************\n" +
                        "   WELCOME TO THE TICKET MANAGEMENT SYSTEM\n" +
                        "*********************************************");

       System.out.println("Y - Add new configuration settings...\nN - Use previous configuration settings");


        Scanner input = new Scanner(System.in);

        String choice = input.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            totalTickets = inputValidityChecker(input, "Enter total tickets available: ");

            ticketReleaseRate = inputValidityChecker(input, "Enter ticket release rate per ticket (How much tickets for a minute): ");

            customerRetrievalRate = inputValidityChecker(input, "Enter customer retrieval rate per ticket (How much tickets for a minute): ");

            maxTicketCapacity = inputValidityChecker(input, "Enter max ticket capacity: ");
        } else if (choice.equals("N")) {
            String filename = "config.json";

            SystemConfiguration config = SystemConfiguration.fromJson(filename);
            if (config != null) {
                totalTickets = config.getTotalTickets();
                ticketReleaseRate = config.getTicketReleaseRate();
                customerRetrievalRate = config.getCustomerRetrievalRate();
                maxTicketCapacity = config.getMaxTicketCapacity();

                System.out.println("Total tickets           : " + totalTickets);
                System.out.println("Ticket release rate     : " + ticketReleaseRate);
                System.out.println("Customer retrieval rate : " + customerRetrievalRate);
                System.out.println("Max ticket capacity     : " + maxTicketCapacity);
            } else {
                System.out.println("Error loading previous configuration. Using default values.");
            }
        }

        System.out.println("--------------------------------");
        System.out.println("System configuration completed!");
        System.out.println("--------------------------------");

        SystemConfiguration config = new SystemConfiguration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        String json = config.toJson();

        try {
            FileWriter writer = new FileWriter("config.json");
            writer.write(json);
//            System.out.println("----------------------------------");
//            System.out.println("Configuration saved to config.json");
//            System.out.println("----------------------------------");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        TicketPool ticketPool = new TicketPool(maxTicketCapacity, totalTickets);

        Thread vendor1 = new Thread(new Vendor(ticketPool,  ticketReleaseRate, "Shone", 2000, 7));
        Thread vendor2 = new Thread(new Vendor(ticketPool,   ticketReleaseRate, "Dasun", 4000, 5));

        Thread customer1 = new Thread(new Customer(ticketPool, "Shanaka", customerRetrievalRate, 5, false));
        Thread customer2 = new Thread(new Customer(ticketPool, "Namidu", customerRetrievalRate, 3, false));
        Thread customer3 = new Thread(new Customer(ticketPool, "Umesh", customerRetrievalRate, 4, true));
        Thread customer4 = new Thread(new Customer(ticketPool, "athif", customerRetrievalRate, 1, true));

        vendor1.start();
        Thread.sleep(2000);
        vendor2.start();
        Thread.sleep(2000);
        customer1.start();
        Thread.sleep(2000);
        customer2.start();
        Thread.sleep(2000);
        customer3.start();
        Thread.sleep(2000);
        customer4.start();
        Thread.sleep(2000);

        while (true) {
            System.out.println("Enter 'stop' to terminate the program or press any key to continue...");
            String command = input.nextLine().toLowerCase();

            if (command.equals("stop")) {
                System.out.println("Stopping the program...");
                break;
            }
        }


        input.close();
        System.exit(0);

    }

    public static int inputValidityChecker(Scanner input, String prompt){
        int value;
        while (true){
            System.out.println(prompt);
            try{
                value = input.nextInt();
                break;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter valid input (Only int applicable)..");
                input.nextLine();
            }
        }return value;
    }


}

