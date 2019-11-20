package pl.waskicompany.netguru.netgururecruitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.waskicompany.netguru.netgururecruitmenttask.model.Movie;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    boolean existsMovieByTitle(String title);

}
