package com.sportradar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonProperty("sport_event_id")
    private String id;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("competition_name")
    private String competition_name;
    @JsonProperty("competitors")
    private List<com.sportradar.model.Competitor> competitors;
    @JsonProperty("venue")
    private com.sportradar.model.Venue venue;
    @JsonProperty("probability_home_team_winner")
    private double probability_home_team_winner;
    @JsonProperty("probability_draw")
    private double probability_draw;
    @JsonProperty("probability_away_team_winner")
    private double probability_away_team_winner;
    private Map<String, Double> highest_probable_result;

}
