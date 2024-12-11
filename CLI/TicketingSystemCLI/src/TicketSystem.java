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

       System.out.println("[Y] - Add new configuration settings...\n[N] - Use previous configuration settings");


        Scanner input = new Scanner(System.in);
        String choice = " ";

        while (true) {
            System.out.print("Do you want to enter values manually? (Y/N): ");
            choice = input.nextLine().toUpperCase();

            if (choice.equals("Y") || choice.equals("N")) {
                break; // Valid input, exit the loop
            } else {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }


        if (choice.equals("Y")) {
            totalTickets = inputValidityChecker(input, "\nEnter total tickets available            : ");

            ticketReleaseRate = inputValidityChecker(input, "\nEnter ticket release rate per ticket\n(How much tickets for a minute)          : ");

            customerRetrievalRate = inputValidityChecker(input, "\nEnter customer retrieval rate per ticket\n(How much tickets for a minute)          : ");

            maxTicketCapacity = inputValidityChecker(input, "\nEnter max ticket capacity                : ");
        } else if (choice.equals("N")) {
            String filename = "config.json";

            SystemConfiguration config = SystemConfiguration.fromJson(filename);
            if (config != null) {
                // If configuration is successfully loaded, use those values.
                totalTickets = config.getTotalTickets();
                ticketReleaseRate = config.getTicketReleaseRate();
                customerRetrievalRate = config.getCustomerRetrievalRate();
                maxTicketCapacity = config.getMaxTicketCapacity();

                System.out.println("\nTotal tickets           : " + totalTickets);
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

        // Now I create a new SystemConfiguration object based on the user's input or loaded configuration.
        SystemConfiguration config = new SystemConfiguration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        // Convert the configuration object into a JSON string for saving. Serialization
        String json = config.toJson();

        try {
            // I write the JSON string to a file (config.json) to save the configuration.
            FileWriter writer = new FileWriter("config.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // I now create a new TicketPool object to hold the tickets available for purchase.
        TicketPool ticketPool = new TicketPool(maxTicketCapacity, totalTickets);

        // Create vendor and customer threads to simulate the ticket management system.
        Thread vendor1 = new Thread(new Vendor(ticketPool,  ticketReleaseRate, "Shone", 2000, 7));
        Thread vendor2 = new Thread(new Vendor(ticketPool,   ticketReleaseRate, "Dasun", 4000, 5));


        Thread customer1 = new Thread(new Customer(ticketPool, "Shanaka", customerRetrievalRate, 5));
        Thread customer2 = new Thread(new Customer(ticketPool, "Namidu", customerRetrievalRate, 3));
        Thread customer3 = new Thread(new Customer(ticketPool, "Umesh", customerRetrievalRate, 4));
        Thread customer4 = new Thread(new Customer(ticketPool, "athif", customerRetrievalRate, 1));

        // Start the vendor and customer threads.
        vendor1.start();
        Thread.sleep(2000);  // Give a short delay before starting the next vendor
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

        // Wait for all threads (vendors and customers) to finish their tasks before continuing.
        vendor1.join();
        vendor2.join();
        customer1.join();
        customer2.join();
        customer3.join();
        customer4.join();


        // I prompt the user to stop the program when desired
        while (true) {
            System.out.println("Enter 'stop' to terminate the program or press any key to continue...");
            String command = input.nextLine().toLowerCase();

            if (command.equals("stop")) {
                // If the user types "stop", I make this to stop the program.
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
            System.out.print(prompt);
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

