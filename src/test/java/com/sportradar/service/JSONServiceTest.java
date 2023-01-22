package com.sportradar.service;

import com.sportradar.model.Competitor;
import com.sportradar.model.Event;
import com.sportradar.model.Events;
import com.sportradar.model.Venue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JSONServiceTest {

    @Test
    void testJSONMapping() throws IOException {

        JSONService jsonService = new JSONService();

        //expected output
        Events expectedEvents = new Events(List.of(new Event("2021-06-22T18:00:00+00:00", "UEFA Champions League", List.of(new Competitor("SS Folgore Falciano Calcio", "San Marino", "home"), new Competitor("FC Prishtina", "Kosovo", "away")), new Venue("Elbasan Arena"), 2.5, 88.1, 9.4)));

        //actual output
        Events actualEvents = jsonService.mapJSON("BE_data_sample.json");

        //assertion
        assertThat(actualEvents).usingRecursiveComparison().isEqualTo(expectedEvents);
    }
}