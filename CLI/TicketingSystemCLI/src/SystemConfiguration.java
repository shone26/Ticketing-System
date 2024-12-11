import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.FileReader;
import java.io.IOException;

public class SystemConfiguration {
    // Configuration values for the ticket system, like total tickets, release rates, and max capacity.
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Constructor to set up the configuration of the system.
    public SystemConfiguration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Convert the configuration object to a JSON string for easy saving or transmission.
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);  // Convert the object into a JSON string
    }

    // Deserialize JSON back into an object (Deserialization)
    public static SystemConfiguration fromJson(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, SystemConfiguration.class);  // Convert JSON string back to SystemConfiguration object
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return null;
        }
    }

    // Getters
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }


    @Override
    public String toString() {
        return "SystemConfiguration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}
