package br.com.screenmatch.dto;

import br.com.screenmatch.model.Category;

import java.util.UUID;

public record SeriesDTO(
        UUID id,
        String title,
        Double imdbRating,
        Integer totalSeasons,
        Category genre,
        String plot,
        String poster,
        String actors
) {}
