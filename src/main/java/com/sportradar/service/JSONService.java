package com.sportradar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportradar.model.Events;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class JSONService {


    public Events mapJSON() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("BE_data.json"), Events.class);
    }
}
