package pl.waskicompany.netguru.netgururecruitmenttask.service;

import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.waskicompany.netguru.netgururecruitmenttask.exception.ResourceAlreadyExistsException;
import pl.waskicompany.netguru.netgururecruitmenttask.exception.ResourceNotFoundException;
import pl.waskicompany.netguru.netgururecruitmenttask.model.Movie;
import pl.waskicompany.netguru.netgururecruitmenttask.repository.MoviesRepository;

import java.util.List;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository repository;

    private final static String OMDB_API_URL = "http://www.omdbapi.com/?apikey=88203b4e";

    public Movie getMovieDetails(Movie movie){
        return Unirest.get(OMDB_API_URL + "&t=" + movie.getTitle())
                .asObject(Movie.class)
                .getBody();
    }

    public List<Movie> getAllMovies(){
        return repository.findAll();
    }

    public Movie createMovie(@RequestBody Movie movie){

        if (repository.existsMovieByTitle(movie.getTitle())){
            throw new ResourceAlreadyExistsException("Movie already exists");
        }

        Movie newMovie = getMovieDetails(movie);
        if (newMovie.getImdbID() == null){
            throw new ResourceNotFoundException("Movie not found");
        }
        return repository.save(newMovie);
    }

    public Movie updateMovie(Long movieId, Movie movieRequest){
        return repository.findById(movieId).map(movie -> {
            movie.setTitle(movieRequest.getTitle());
            return repository.save(movie);
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }

    public ResponseEntity<Object> deleteMovie(Long movieId){
        return repository.findById(movieId).map(movie -> {
            repository.delete(movie);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }

}
