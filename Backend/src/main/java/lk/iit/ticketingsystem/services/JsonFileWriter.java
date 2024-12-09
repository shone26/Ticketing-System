package lk.iit.ticketingsystem.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iit.ticketingsystem.Models.Configuration;
//import lk.iit.ticketingsystem.Models.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class JsonFileWriter {

    private final ObjectMapper objectMapper;

    public JsonFileWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void writeUserToFile(Configuration config, String fileName) throws IOException {
        // Convert the User object to JSON and write it to a file
        objectMapper.writeValue(new File(fileName), config);
    }
}
