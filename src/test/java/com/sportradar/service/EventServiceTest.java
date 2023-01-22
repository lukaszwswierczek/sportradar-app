package com.sportradar.service;

import com.sportradar.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertEquals(expectedTeams, actualTeams);
    }

    @Test
    void testConvertToDto() {

        //expected output
        List<EventDto> expectedEventDto = Arrays.asList(new EventDto("2021-06-22T18:00:00+00:00", "UEFA Champions League", List.of(new Competitor("SS Folgore Falciano Calcio", "San Marino", "home"), new Competitor("FC Prishtina", "Kosovo", "away")), new Venue("Elbasan Arena"), 2.5, 88.1, 9.4, Map.of("DRAW", 88.1)));

        //actual output
        EventService service = new EventService(new JSONService());
        List<Event> event = Arrays.asList(new Event("2021-06-22T18:00:00+00:00", "UEFA Champions League", List.of(new Competitor("SS Folgore Falciano Calcio", "San Marino", "home"), new Competitor("FC Prishtina", "Kosovo", "away")), new Venue("Elbasan Arena"), 2.5, 88.1, 9.4, Map.of("DRAW", 88.1)));
        List<EventDto> actualEventsDto = service.convertToDto(event);

        assertThat(actualEventsDto).usingRecursiveComparison().isEqualTo(expectedEventDto);
    }
}