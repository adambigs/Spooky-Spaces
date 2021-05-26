package learn.spooky.controller;

import learn.spooky.domain.CommentService;
import learn.spooky.domain.Result;
import learn.spooky.models.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findById(@PathVariable int commentId){
        Comment comment = service.findById(commentId);
        if (comment== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{encounterId}")
    public ResponseEntity<List> findByEncounter(@PathVariable int encounterId){
        List<Comment> comments= service.findByEncounter(encounterId);
        if (comments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Comment comment) {
        Result<Comment> result = service.add(comment);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorHandler.build(result);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Object> update(@PathVariable int commentId, @RequestBody Comment comment) {
        if (commentId != comment.getCommentId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Comment> result = service.update(comment);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> deleteById(@PathVariable int commentId) {
        Result<Comment> result = service.deleteById(commentId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }
}
