package com.sportradar.controller;
import com.sportradar.model.Event;
import com.sportradar.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

@Controller
public class AppController {

    private final EventService eventService;

    public AppController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public List<Event> printInformation() throws ParseException, IOException {
        return eventService.returnMostProbableResults();
    }

    @GetMapping("/teams")
    public List<String> printTeams() throws IOException {
        return eventService.printUniqueTeams();
    }

}
