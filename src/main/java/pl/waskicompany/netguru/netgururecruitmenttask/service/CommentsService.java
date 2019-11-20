package pl.waskicompany.netguru.netgururecruitmenttask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.waskicompany.netguru.netgururecruitmenttask.exception.ResourceNotFoundException;
import pl.waskicompany.netguru.netgururecruitmenttask.model.Comment;
import pl.waskicompany.netguru.netgururecruitmenttask.repository.CommentsRepository;
import pl.waskicompany.netguru.netgururecruitmenttask.repository.MoviesRepository;

@Service
public class CommentsService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public Page<Comment> getAllCommentsByMovieId(Long movieId, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(0,pageable.getPageSize(), Sort.by("text"));
        return commentsRepository.findByMovieId(movieId, sortedPageable);
    }

    public Comment createComment(Long movieId, Comment comment) {
        return moviesRepository.findById(movieId).map(movie -> {
            comment.setMovie(movie);
            return commentsRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }

    public Comment updateComment(Long movieId, Long commentId, Comment commentRequest) {
        if (!moviesRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("MovieId " + movieId + " not found");
        }

        return commentsRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentsRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

    public ResponseEntity<?> deleteComment(Long movieId, Long commentId) {
        return commentsRepository.findByIdAndMovieId(commentId, movieId).map(comment -> {
            commentsRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and movieId " + movieId));
    }

}
