package br.com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Essa anotação é usada para ignorar propriedades que não são mapeadas para a classe
@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(
        String Title,
        String imdbRating,
        Integer totalSeasons,
        String Genre,
        String Plot,
        String Poster,
        String Actors
) {

}
