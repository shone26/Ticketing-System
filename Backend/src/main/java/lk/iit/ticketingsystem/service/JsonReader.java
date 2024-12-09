package lk.iit.ticketingsystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iit.ticketingsystem.Models.Configuration;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    public static Configuration readConfigFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), Configuration.class);
    }
}