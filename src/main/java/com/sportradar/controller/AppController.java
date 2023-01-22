package com.sportradar.controller;
import com.sportradar.model.Event;
import com.sportradar.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

@RestController
public class AppController {

    private final EventService eventService;

    public AppController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Event>> printInformation() throws ParseException, IOException {
        return new ResponseEntity<>(eventService.returnMostProbableResults(), HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<String>> printTeams() throws IOException {
        return new ResponseEntity<>(eventService.printUniqueTeams(),HttpStatus.OK);
    }

}
