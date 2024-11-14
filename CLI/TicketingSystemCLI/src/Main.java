import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ticket_system";
        String user = "shone";
        String password = "abc@123";

        Connection connection = null;

        try {
             connection= DriverManager.getConnection(
                    url, user, password
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");

//            while (resultSet.next()){
//                System.out.println(resultSet.getString("username"));
//                System.out.println(resultSet.getString("password"));
//            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(
                "*********************************************\n" +
                "   WELCOME TO THE TICKET MANAGEMENT SYSTEM\n" +
                "*********************************************\n" +
                "\n" +
                "Your one-stop solution for efficient event ticket tracking and management.\n" +
                "\n" +
                "\uD83D\uDE80 Features at your fingertips:\n" +
                "  1\uFE0F⃣ View the Events going to happen.\n" +
                "  2\uFE0F⃣ Purchase a Ticket.\n" +
                "  3\uFE0F⃣ Cancel your Ticket.\n" +
                "  4\uFE0F⃣ View Tickets you bought.\n" +
                "  5\uFE0F⃣ Contact with the Vender.\n" +
                "\n" +
                "\uD83D\uDEE0\uFE0F Let's get started! Choose an option from the menu below.");

    }
}