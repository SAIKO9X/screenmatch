package br.com.screenmatch.controller;

import br.com.screenmatch.dto.EpisodesDTO;
import br.com.screenmatch.dto.SeriesDTO;
import br.com.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping
    public List<SeriesDTO> getSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SeriesDTO> getTop5Series() {
        return seriesService.getTop5Series();
    }

    @GetMapping("/releases")
    public List<SeriesDTO> getRealeds() {
        return seriesService.getTop5ReleasedSeries();
    }

    @GetMapping("/{id}")
    public SeriesDTO getSeriesById(@PathVariable UUID id) {
        return seriesService.getSeriesById(id);
    }

    @GetMapping("/{id}/seasons/todas")
    public List<EpisodesDTO> getAllSeasons(@PathVariable UUID id) {
        return seriesService.getAllSeasons(id);
    }

    @GetMapping("/{id}/seasons/{season}")
    public List<EpisodesDTO> getSeason(@PathVariable UUID id, @PathVariable Integer season) {
        return seriesService.getSeason(id, season);
    }

    @GetMapping("/category/{genre}")
    public List<SeriesDTO> getSeriesByGenre(@PathVariable String genre) {
        return seriesService.getSeriesByGenre(genre);
    }
}
