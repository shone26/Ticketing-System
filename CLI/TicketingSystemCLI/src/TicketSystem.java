import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketSystem {
    public static void main(String[] args) throws InterruptedException {
        int totalTickets;
        int ticketReleaseRate;
        int customerRetrievalRate;
        int maxTicketCapacity;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter total tickets available: ");
        totalTickets = input.nextInt();

        System.out.println("Enter ticket release rate per ticket (How much tickets for a minute): ");
        ticketReleaseRate = input.nextInt();

        System.out.println("Enter customer retrieval rate per ticket (How much tickets for a minute): ");
        customerRetrievalRate = input.nextInt();

        System.out.println("Enter max ticket capacity: ");
        maxTicketCapacity = input.nextInt();

        System.out.println("System configuration completed!");
        input.close();

        List<Ticket> newTicketList = new ArrayList<>();
        for (int i = 0; i < totalTickets; i++) {
            newTicketList.add(new Ticket(1, 100.0));
        }

        newTicketList.add(new Ticket(1, 100.0));
        newTicketList.add(new Ticket(2, 150.0));
        newTicketList.add(new Ticket(3, 120.0));

        TicketPool ticketPool = new TicketPool(maxTicketCapacity, totalTickets);

        Thread vendor1 = new Thread(new Vendor(ticketPool, totalTickets, ticketReleaseRate, "Shone", 2000, 7));
        Thread vendor2 = new Thread(new Vendor(ticketPool, totalTickets,  ticketReleaseRate, "Dasun", 4000, 5));

        Thread customer1 = new Thread(new Customer(ticketPool, "Shanaka", customerRetrievalRate, 5, false));
        Thread customer2 = new Thread(new Customer(ticketPool, "Namidu", customerRetrievalRate, 3, false));
        Thread customer3 = new Thread(new Customer(ticketPool, "Umesh", customerRetrievalRate, 4, true));

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



//        TicketSystem system = new TicketSystem();
//        system.connection();
//        system.runCLI();
//        system.inputMenuOption();
    }

//    public void runCLI(){
//        System.out.println(
//                "*********************************************\n" +
//                        "   WELCOME TO THE TICKET MANAGEMENT SYSTEM\n" +
//                        "*********************************************\n" +
//                        "\n" +
//                        "Your one-stop solution for efficient event ticket tracking and management.\n" +
//                        "\n" +
//                        "\uD83D\uDE80 Features at your fingertips:\n" +
//                        "  1\uFE0F⃣ View the Events going to happen.\n" +
//                        "  2\uFE0F⃣ Purchase a Ticket.\n" +
//                        "  3\uFE0F⃣ Cancel your Ticket.\n" +
//                        "  4\uFE0F⃣ View Tickets you bought.\n" +
//                        "  5\uFE0F⃣ Contact with the Vender.\n" +
//                        "\n" +
//                        "\uD83D\uDEE0\uFE0F Let's get started! Choose an option from the menu below.");
//
//
//    }
//
//    public void inputMenuOption(){
//        Scanner scanner = new Scanner(System.in);
//
//        int option = validateInput(scanner.nextInt());
//
//    }
//
//    private static int validateInput(int option) {
//        while (true) {
//            try {
//                if (option > 0 && option <= 5) {
//                    return option;
//                } else {
//                    System.out.print("Please enter a valid option: ");
//                }
//            } catch (NumberFormatException e) {
//                System.out.print("Invalid input. Please enter a valid number: ");
//            }
//        }
//    }
//

//    public void connection(){
//        String url = "jdbc:mysql://localhost:3306/event_ticket_db";
//        String user = "shone";
//        String password = "abc@123";
//
//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection(
//                    url, user, password
//            );
//            System.out.println("Connection successful!");
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from config_info");
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("config_name"));
//                System.out.println(resultSet.getString("config_parameter"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

// need to randomise how many tickets are purchase a time by the customer.