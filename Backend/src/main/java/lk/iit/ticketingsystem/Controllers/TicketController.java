package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.service.TicketPoolService;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/tickets")
public class TicketController {


    private TicketPoolService ticketPoolService;

    // Endpoint to add tickets (by vendor)
//    @PostMapping("/add")
//    public String addTickets(@RequestBody TicketRequest ticketRequest) {
//        ticketPool.addTickets(ticketRequest.getReleaseRate(), ticketRequest.getPrice(),
//                ticketRequest.getVendorName(), ticketRequest.getAmount());
//        return "Tickets added successfully!";
//    }


    // Endpoint to purchase tickets (by customer)
    @PostMapping("/purchase")
    public String purchaseTickets(
            @RequestParam int rate,
            @RequestParam String customerName,
            @RequestParam int amount) {
        boolean success = ticketPoolService.removeTicket(rate, customerName, amount);
        return success ? "Tickets purchased successfully!" : "Failed to purchase tickets.";
    }

    // Endpoint to get available tickets
    @GetMapping("/available")
    public int getAvailableTickets() {
        return ticketPoolService.getAvailableTickets();
    }
}