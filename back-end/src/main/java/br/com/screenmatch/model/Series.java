package br.com.screenmatch.model;

import br.com.screenmatch.service.GPTTranslatorService;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_series")
public class Series {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String title;
    private Double imdbRating;
    private Integer totalSeasons;
    @Enumerated(EnumType.STRING)
    private Category genre;
    private String plot;
    private String poster;
    private String actors;
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes = new ArrayList<>();

    public Series() {
    }

    public Series(SeriesData seriesData) {
        this.title = seriesData.Title();
        this.totalSeasons = seriesData.totalSeasons();
        this.imdbRating = seriesData.imdbRating() != null ? Double.valueOf(seriesData.imdbRating()) : null;
        this.genre = Category.fromString(seriesData.Genre().split(",")[0].trim());
        this.plot = GPTTranslatorService.translate(seriesData.Plot()).trim();
        this.poster = seriesData.Poster();
        this.actors = seriesData.Actors() != null ? seriesData.Actors() : "";
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(episode -> episode.setSeries(this));
        this.episodes = episodes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return
            "Genêro: " + genre +
            " Titulo: " + title +
            " Nota: " + imdbRating +
            " Temporadas: " + totalSeasons +
            " Sinopse: " + plot +
            " Capa: " + poster +
            " Atores: " + actors +
            " Episódios: " + episodes;
}
}
