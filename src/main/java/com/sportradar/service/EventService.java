package com.sportradar.service;
import com.sportradar.mapper.JSONMapper;
import com.sportradar.model.Competitor;
import com.sportradar.model.Event;
import com.sportradar.model.Events;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventService {

    private final JSONMapper jsonMapper;

    public EventService(JSONMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public Events setHighestProbableResult() throws IOException {

        Events allEvents = jsonMapper.mapJSON();
        for (Event event : allEvents.getEvents()) {
            if (event.getProbability_draw() > event.getProbability_away_team_winner() && event.getProbability_draw() > event.getProbability_home_team_winner()) {
                event.setHighest_probable_result(Map.of("DRAW", event.getProbability_draw()));
            } else if (event.getProbability_away_team_winner() > event.getProbability_home_team_winner()) {
                event.setHighest_probable_result(Map.of("AWAY_TEAM_WIN", event.getProbability_away_team_winner()));
            } else {
                event.setHighest_probable_result(Map.of("HOME_TEAM_WIN", event.getProbability_home_team_winner()));
            }
        };
        return allEvents;
    }

    public List<Event> returnMostProbableResults() throws ParseException, IOException {

        Events allEvents = setHighestProbableResult();
        List<Event> events = allEvents.getEvents();
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return Double.compare(e2.getHighest_probable_result().values().iterator().next(), e1.getHighest_probable_result().values().iterator().next());
            }
        });

        List<Event> top10Events = events.subList(0, 10);
        printMatchesInfo(top10Events);
        return top10Events;
    }

    public void returnMostProbableResults(int noOfMatches) throws ParseException, IOException {

        Events allEvents = setHighestProbableResult();
        if(noOfMatches < 1 || noOfMatches > 25 ){
            throw new IllegalArgumentException("Please choose no. of matches in a range of 1-25.");
        }
        List<Event> events = allEvents.getEvents();
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return Double.compare(e2.getHighest_probable_result().values().iterator().next(), e1.getHighest_probable_result().values().iterator().next());
            }
        });
        List<Event> topEvents = events.subList(0, noOfMatches);
        printMatchesInfo(topEvents);
    }

    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return format.parse(dateString);
    }

    public void printMatchesInfo(List<Event> matches) throws ParseException {

        for (Event event : matches) {
            List<Competitor> competitors = event.getCompetitors();
            Competitor competitor1 = competitors.get(0);
            Competitor competitor2 = competitors.get(1);

            System.out.println("Start date: " + parseDate(event.getStartDate()));
            System.out.println(competitor1.getName() + " (" + competitor1.getCountry() + ") vs. " + competitor2.getName() + " (" + competitor2.getCountry() + ") ");
            System.out.println("Venue: " + event.getVenue().getName());
            System.out.println("Highest probable result: " + event.getHighest_probable_result().entrySet().iterator().next().getKey() + " (" + event.getHighest_probable_result().entrySet().iterator().next().getValue() + ")");
            System.out.println("--------------------------------------");
        }
    }

    public void printUniqueTeams(Events events){

        HashSet<String> uniqueTeams = new HashSet<>();
        for (Event event : events.getEvents()) {
            List<Competitor> competitors = event.getCompetitors();
            Competitor competitor1 = competitors.get(0);
            Competitor competitor2 = competitors.get(1);
            uniqueTeams.add(competitor1.getName());
            uniqueTeams.add(competitor2.getName());
        }
//        for (String uniqueTeam : uniqueTeams) {
//            System.out.println(uniqueTeam);
//        }
    }

    public List<String> printTeamsInGivenCompetition(String competitionName) throws IOException {

        Events events = setHighestProbableResult();
        List<Event> matchingEvents = events.getEvents().stream().filter(event -> event.getCompetition_name().equals(competitionName)).toList();
        System.out.println("Matches for competition: " + competitionName + "\n----------" );
        HashSet<String> teamNames = new HashSet<>();
        for (Event matchingEvent : matchingEvents) {
            List<Competitor> competitors = matchingEvent.getCompetitors();
            Competitor competitor1 = competitors.get(0);
            Competitor competitor2 = competitors.get(1);
            teamNames.add(competitor1.getName());
            teamNames.add(competitor2.getName());
        }
        List<String> teamNamesList = teamNames.stream().sorted().toList();
        for (String s : teamNamesList) {
            System.out.println(s);
        }
        return teamNamesList;
    }

}
