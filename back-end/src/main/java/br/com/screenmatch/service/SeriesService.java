package br.com.screenmatch.service;

import br.com.screenmatch.dto.EpisodesDTO;
import br.com.screenmatch.dto.SeriesDTO;
import br.com.screenmatch.model.Category;
import br.com.screenmatch.model.Series;
import br.com.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    @Autowired
    private SerieRepository serieRepository;

    private List<SeriesDTO> convertData(List<Series> s) {
        return s.stream()
                .map(series -> new SeriesDTO(
                        series.getId(),
                        series.getTitle(),
                        series.getImdbRating(),
                        series.getTotalSeasons(),
                        series.getGenre(),
                        series.getPlot(),
                        series.getPoster(),
                        series.getActors()
                ))
                .collect(Collectors.toList());
    }

    public List<SeriesDTO> getAllSeries() {
        return convertData(serieRepository.findAll());
    }

    public List<SeriesDTO> getTop5Series() {
        return convertData(serieRepository.findTop5ByOrderByImdbRatingDesc());

    }

    public List<SeriesDTO> getTop5ReleasedSeries() {
        return convertData(serieRepository.latestReleases());
    }

    public SeriesDTO getSeriesById(UUID id) {
        Optional<Series> series = serieRepository.findById(id);

        if (series.isPresent()) {
            Series s = series.get();
            return new SeriesDTO(
                    s.getId(),
                    s.getTitle(),
                    s.getImdbRating(),
                    s.getTotalSeasons(),
                    s.getGenre(),
                    s.getPlot(),
                    s.getPoster(),
                    s.getActors()
            );
        }
        return null;
    }

    public List<EpisodesDTO> getAllSeasons(UUID id) {
        Optional<Series> series = serieRepository.findById(id);

        if (series.isPresent()) {
            Series s = series.get();
            return s.getEpisodes().stream()
                    .map(episode -> new EpisodesDTO(
                            episode.getTitle(),
                            episode.getSeason(),
                            episode.getEpisode(),
                            episode.getImdbRating()
                    ))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodesDTO> getSeason(UUID id, Integer season) {
        return serieRepository.getEpisodeBySeason(id, season).stream()
                .map(episode -> new EpisodesDTO(
                        episode.getTitle(),
                        episode.getSeason(),
                        episode.getEpisode(),
                        episode.getImdbRating()
                ))
                .collect(Collectors.toList());
    }

    public List<SeriesDTO> getSeriesByGenre(String genre) {
        Category category = Category.fromPortuguese(genre);
        return convertData(serieRepository.findByGenre(category));
    }
}
