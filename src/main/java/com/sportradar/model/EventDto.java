package com.sportradar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String startDate;
    private String competition_name;
    private List<Competitor> competitors;
    private Venue venue;
    private double probability_home_team_winner;
    private double probability_draw;
    private double probability_away_team_winner;
    private Map<String, Double> highest_probable_result;
}
