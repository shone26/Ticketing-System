package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.Models.Configuration;
//import lk.iit.ticketingsystem.Models.User;
import lk.iit.ticketingsystem.services.JsonFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ConfigurationToJsonFileController {

    @Autowired
    private JsonFileWriter jsonFileWriter;

//    @GetMapping("/write-user-json")
//    public String writeUserJson() {
//        try {
//            // Create a User object
//            User user = new User("umesh rasanjana", "shine", "password123", "shone@example.com");
//            // Write to a file called 'user.json'
//            jsonFileWriter.writeUserToFile(user, "user.json");
//            return "User JSON written successfully!";
//        } catch (IOException e) {
//            return "Error writing to file: " + e.getMessage();
//        }
//    }

    @GetMapping("/write-config-json")
    public String writeConfigJson() {
        try {
            // Create a User object
            Configuration config = new Configuration(20, 40, 40, 50);
            // Write to a file called 'user.json'
            jsonFileWriter.writeUserToFile(config, "config.json");
            return "User JSON written successfully!";
        } catch (IOException e) {
            return "Error writing to file: " + e.getMessage();
        }
    }
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
