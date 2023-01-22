package com.sportradar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    public Event(String startDate, String competition_name, List<Competitor> competitors, Venue venue, double probability_home_team_winner, double probability_draw, double probability_away_team_winner) {
        this.startDate = startDate;
        this.competition_name = competition_name;
        this.competitors = competitors;
        this.venue = venue;
        this.probability_home_team_winner = probability_home_team_winner;
        this.probability_draw = probability_draw;
        this.probability_away_team_winner = probability_away_team_winner;
    }

    public Event(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("competition_name")
    private String competition_name;
    @JsonProperty("competitors")
    private List<Competitor> competitors;
    @JsonProperty("venue")
    private Venue venue;
    @JsonProperty("probability_home_team_winner")
    private double probability_home_team_winner;
    @JsonProperty("probability_draw")
    private double probability_draw;
    @JsonProperty("probability_away_team_winner")
    private double probability_away_team_winner;
    private Map<String, Double> highest_probable_result;

}
