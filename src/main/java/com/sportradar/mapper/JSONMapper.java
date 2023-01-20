package com.sportradar.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSONMapper {


    public com.sportsradar.model.Events mapJSON() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("BE_data.json"), com.sportsradar.model.Events.class);
    }
}
