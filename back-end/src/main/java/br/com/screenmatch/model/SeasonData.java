package br.com.screenmatch.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(Integer Season, List<EpisodeData> Episodes) {
  
}
