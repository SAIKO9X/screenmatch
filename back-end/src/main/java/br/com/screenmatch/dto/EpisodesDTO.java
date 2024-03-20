package br.com.screenmatch.dto;

public record EpisodesDTO(
        String title,
        Integer season,
        Integer episode,
        Double imdbRating
) {
}
