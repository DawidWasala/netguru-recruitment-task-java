package pl.waskicompany.netguru.netgururecruitmenttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waskicompany.netguru.netgururecruitmenttask.model.Movie;
import pl.waskicompany.netguru.netgururecruitmenttask.service.MoviesService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MoviesController {

    @Autowired
    private MoviesService service;

    @GetMapping("/movies")
    ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }

    @PostMapping("/movies")
    ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(service.createMovie(movie));
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long movieId, @Valid @RequestBody Movie movieRequest) {
        return ResponseEntity.ok(service.updateMovie(movieId, movieRequest));
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(service.deleteMovie(movieId));
    }

}
