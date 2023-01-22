package com.sportradar;

import com.sportradar.service.EventService;
import com.sportradar.service.JSONService;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {

        JSONService jsonService = new JSONService();
        EventService eventService = new EventService(jsonService);

        //print top10 most probable results
        eventService.returnMostProbableResults();
        //print most probable results with number in parameter
//        eventService.returnMostProbableResults(4);
        //print team names
//        eventService.printUniqueTeams();
    }
}
