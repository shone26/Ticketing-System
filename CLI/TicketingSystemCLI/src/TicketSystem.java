import java.sql.*;
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

        System.out.println("Enter ticket release rate per ticket (How much for a minute): ");
        ticketReleaseRate = input.nextInt();

        System.out.println("Enter customer retrieval rate per ticket (How much for a minute): ");
        customerRetrievalRate = input.nextInt();

        System.out.println("Enter max ticket capacity: ");
        maxTicketCapacity = input.nextInt();

        System.out.println("System configuration completed!");
        input.close();

        TicketPool ticketPool = new TicketPool(maxTicketCapacity, totalTickets);


        Thread vendor1 = new Thread(new Vendor(ticketPool, "Spandana", ticketReleaseRate, "Shone", 2000));
        Thread vendor2 = new Thread(new Vendor(ticketPool, "Inter-Splash", ticketReleaseRate, "Dasun", 4000));

        Thread customer1 = new Thread(new Customer(ticketPool, "Shanaka", customerRetrievalRate));
        Thread customer2 = new Thread(new Customer(ticketPool, "Namidu", customerRetrievalRate));
        Thread customer3 = new Thread(new Customer(ticketPool, "Umesh", customerRetrievalRate));

        vendor1.start();
//        Thread.sleep(100);
        vendor2.start();
//        Thread.sleep(100);
        customer1.start();
//        Thread.sleep(100);
        customer2.start();
//        Thread.sleep(100);
        customer3.start();
//        Thread.sleep(100);



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