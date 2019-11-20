package pl.waskicompany.netguru.netgururecruitmenttask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.waskicompany.netguru.netgururecruitmenttask.models.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {
}
