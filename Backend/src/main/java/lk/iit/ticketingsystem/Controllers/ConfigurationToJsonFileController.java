package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.Models.Configuration;
//import lk.iit.ticketingsystem.Models.User;
import lk.iit.ticketingsystem.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/config")
public class ConfigurationToJsonFileController {

    @Autowired
    private TicketPoolService.JsonFileWriter jsonFileWriter;

    @PostMapping("/write-config-json")
    public String writeConfigJson(@RequestBody Configuration config) {
        try {
            // Write to a file called 'config.json'
            jsonFileWriter.writeUserToFile(config, "config.json");
            return "User JSON written successfully!";
        } catch (IOException e) {
            return "Error writing to file: " + e.getMessage();
        }
    }
}
