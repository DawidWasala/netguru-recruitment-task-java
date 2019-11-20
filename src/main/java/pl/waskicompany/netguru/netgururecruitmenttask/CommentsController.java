package pl.waskicompany.netguru.netgururecruitmenttask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waskicompany.netguru.netgururecruitmenttask.models.Comment;

import javax.validation.Valid;

@RestController
public class CommentsController {

    private final MoviesRepository moviesRepository;

    private final CommentsRepository commentsRepository;

    public CommentsController(MoviesRepository moviesRepository, CommentsRepository commentsRepository) {
        this.moviesRepository = moviesRepository;
        this.commentsRepository = commentsRepository;
    }

    @GetMapping("/movies/{movieId}/comments")
    public Page<Comment> getAllCommentsByMovieId(@PathVariable (value = "movieId") Long movieId, Pageable pageable){
        return commentsRepository.findByMovieId(movieId,pageable);
    }

    @PostMapping("/movies/{movieId}/comments")
    public Comment createComment(@PathVariable (value = "movieId") Long movieId, @Valid @RequestBody Comment comment){
        return moviesRepository.findById(movieId).map(movie -> {
            comment.setMovie(movie);
            return commentsRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }

    @PutMapping("/movies/{movieId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "movieId") Long movieId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest){
        if(!moviesRepository.existsById(movieId)){
            throw new ResourceNotFoundException("MovieId " + movieId + " not found");
        }

        return commentsRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentsRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

    @DeleteMapping("/movies/{movieId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "movieId") Long movieId,
                                           @PathVariable (value = "commentId") Long commentId){
        return commentsRepository.findByIdAndMovieId(commentId, movieId).map(comment -> {
            commentsRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and movieId " + movieId));
    }


}
