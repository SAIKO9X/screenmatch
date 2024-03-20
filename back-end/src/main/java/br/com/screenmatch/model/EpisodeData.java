package br.com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(String Title, @JsonAlias("Released") String releasedDate, Integer Episode, String imdbRating) {
  
}
