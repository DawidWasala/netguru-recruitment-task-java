package pl.waskicompany.netguru.netgururecruitmenttask;

import kong.unirest.Unirest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waskicompany.netguru.netgururecruitmenttask.models.Movie;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MoviesController {

    private final static String OMDB_API_URL = "http://www.omdbapi.com/?apikey=88203b4e";

    private final MoviesRepository repository;

    public MoviesController(MoviesRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/movies")
    List<Movie> getAllMovies(){
       return repository.findAll();
    }

    @PostMapping("/movies")
    Movie createMovie(@RequestBody Movie movie){
        movie = getMovieDetails(movie);
        if (movie.getImdbID() == null){
            throw new ResourceNotFoundException("Movie not found");
        }
        return repository.save(movie);
    }

    @PutMapping("/movies/{movieId}")
    public Movie updateMovie(@PathVariable Long movieId, @Valid @RequestBody Movie movieRequest){
        return repository.findById(movieId).map(movie -> {
            movie.setTitle(movieRequest.getTitle());
            return repository.save(movie);
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId){
        return repository.findById(movieId).map(movie -> {
            repository.delete(movie);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }

    private Movie getMovieDetails(Movie movie){
        Movie newMovie = Unirest.get(OMDB_API_URL + "&t=" + movie.getTitle())
                .asObject(Movie.class)
                .getBody();
        return newMovie;
    }



}
