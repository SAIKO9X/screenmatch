package br.com.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "tb_episode")
public class Episode {
  @Id()
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private Integer Season;
  private Integer Episode;
  private String Title;
  private LocalDate releasedDate;
  private Double imdbRating;
  @ManyToOne
  private Series series;

  public Episode() {}

  public Episode(Integer seasonNumber, EpisodeData episodeData) {
    this.Season = seasonNumber;
    this.Episode = episodeData.Episode();
    this.Title = episodeData.Title();
    this.releasedDate = episodeData.releasedDate().equalsIgnoreCase("N/A") ? null : LocalDate.parse(episodeData.releasedDate());
    this.imdbRating = episodeData.imdbRating().equalsIgnoreCase("N/A") ? null : Double.parseDouble(episodeData.imdbRating());
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Series getSeries() {
    return series;
  }

  public void setSeries(Series series) {
    this.series = series;
  }

  public Integer getSeason() {
    return Season;
  }

  public void setSeason(Integer season) {
    Season = season;
  }

  public Integer getEpisode() {
    return Episode;
  }

  public void setEpisode(Integer episode) {
    Episode = episode;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public LocalDate getReleasedDate() {
    return releasedDate;
  }

  public void setReleasedDate(LocalDate releasedDate) {
    this.releasedDate = releasedDate;
  }

  public Double getImdbRating() {
    return imdbRating;
  }

  public void setImdbRating(Double imdbRating) {
    this.imdbRating = imdbRating;
  }

  @Override
  public String toString() {
    return "Série: " + series.getTitle() +
           ", Temporada: " + Season +
           ", Episodio: " + Episode + 
           ", Titulo: " + Title + 
           ", Data de Lançamento: " + releasedDate +
           ", Nota no IMDB: " + imdbRating;
  }
}
