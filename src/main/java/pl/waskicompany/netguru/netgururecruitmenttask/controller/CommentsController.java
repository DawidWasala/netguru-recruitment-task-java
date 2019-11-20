package pl.waskicompany.netguru.netgururecruitmenttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waskicompany.netguru.netgururecruitmenttask.model.Comment;
import pl.waskicompany.netguru.netgururecruitmenttask.service.CommentsService;

import javax.validation.Valid;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService service;

    @GetMapping("/movies/{movieId}/comments")
    public ResponseEntity<Page<Comment>> getAllCommentsByMovieId(@PathVariable(value = "movieId") Long movieId, Pageable pageable) {
        return ResponseEntity.ok(service.getAllCommentsByMovieId(movieId, pageable));
    }

    @PostMapping("/movies/{movieId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "movieId") Long movieId, @Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(service.createComment(movieId, comment));
    }

    @PutMapping("/movies/{movieId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "movieId") Long movieId,
                                                 @PathVariable(value = "commentId") Long commentId,
                                                 @Valid @RequestBody Comment commentRequest) {
        return ResponseEntity.ok(service.updateComment(movieId, commentId, commentRequest));
    }

    @DeleteMapping("/movies/{movieId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "movieId") Long movieId,
                                           @PathVariable(value = "commentId") Long commentId) {
        return ResponseEntity.ok(service.deleteComment(movieId, commentId));
    }


}
