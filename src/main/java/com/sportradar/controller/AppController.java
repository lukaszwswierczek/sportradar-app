package com.sportradar.controller;

import com.sportradar.model.Event;
import com.sportradar.model.EventDto;
import com.sportradar.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AppController {

    private final EventService eventService;

    public AppController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EventDto>> printInformation() throws IOException, ParseException {
        List<Event> topEvents = eventService.returnMostProbableResults();
        return new ResponseEntity<>(eventService.convertToDto(topEvents), HttpStatus.OK);
    }

    @GetMapping("/{noOfMatches}")
    public ResponseEntity<List<EventDto>> printInformation(@PathVariable("noOfMatches") int noOfMatches) throws IOException, ParseException {
        List<Event> topEvents = eventService.returnMostProbableResults(noOfMatches);
        return new ResponseEntity<>(eventService.convertToDto(topEvents), HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<String>> printTeams() throws IOException {
        return new ResponseEntity<>(eventService.printUniqueTeams(), HttpStatus.OK);
    }

}
