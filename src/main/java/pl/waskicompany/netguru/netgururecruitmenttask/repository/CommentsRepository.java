package pl.waskicompany.netguru.netgururecruitmenttask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.waskicompany.netguru.netgururecruitmenttask.model.Comment;

import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByMovieId(Long movieId, Pageable pageable);
    Optional<Comment> findByIdAndMovieId(Long id, Long postId);

}
