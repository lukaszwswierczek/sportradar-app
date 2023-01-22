package com.sportradar.service;

import com.sportradar.model.Competitor;
import com.sportradar.model.Event;
import com.sportradar.model.Events;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Test
    void testPrintUniqueTeams() throws IOException {

        //expected output
        Events allEvents = new Events(Arrays.asList(
                new Event(Arrays.asList(new Competitor("SS Folgore Falciano Calcio", "San Marino", "home"), new Competitor("FC Prishtina", "Kosovo", "away"))),
                new Event(Arrays.asList(new Competitor("SS Folgore Falciano Calcio", "San Marino", "home"), new Competitor("HB Torshavn", "Faroe Islands", "away"))))
        );
        List<String> expectedTeams = Arrays.asList("FC Prishtina", "HB Torshavn", "SS Folgore Falciano Calcio");

        //mock & actual output
        EventService eventService = spy(new EventService(new JSONService()));
        when(eventService.setHighestProbableResult()).thenReturn(allEvents);
        List<String> actualTeams = eventService.printUniqueTeams();

        //assertion
        assertEquals(expectedTeams,actualTeams);
    }
}