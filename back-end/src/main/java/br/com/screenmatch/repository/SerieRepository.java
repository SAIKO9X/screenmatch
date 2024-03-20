package br.com.screenmatch.repository;

import br.com.screenmatch.model.Category;
import br.com.screenmatch.model.Episode;
import br.com.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Extendemos JpaRepository para que a interface SerieRepository possa ter acesso a métodos de CRUD
public interface SerieRepository extends JpaRepository<Series, UUID> {
    Optional<Series> findByTitleContainingIgnoreCase(String title);

    List<Series> findByActorsContainingIgnoreCase(String actor);

    List<Series> findTop5ByOrderByImdbRatingDesc();

    List<Series> findByGenre(Category category);

    @Query("SELECT s FROM Series s where s.totalSeasons <= :totalSeasons AND s.imdbRating >= :imdbRating")
    List<Series> seriesBySeasonAndRating(int totalSeasons, double imdbRating);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.Title ILIKE %:excerpt%")
    List<Episode> episodesByExcerpt(String excerpt);

    @Query("SELECT s FROM Series s " + "JOIN s.episodes e " + "GROUP BY s " + "ORDER BY MAX(e.releasedDate) DESC LIMIT 5")
    List<Series> latestReleases();

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s.id = :id AND e.Season = :season")
    List<Episode> getEpisodeBySeason(UUID id, Integer season);
}
