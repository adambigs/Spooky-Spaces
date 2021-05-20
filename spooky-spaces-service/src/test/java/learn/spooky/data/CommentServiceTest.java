package learn.spooky.domain;

import learn.spooky.data.CommentRepository;
import learn.spooky.models.Comment;
import learn.spooky.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CommentServiceTest {

    @Autowired
    CommentService service;

    @MockBean
    CommentRepository repository;

    //should add
    @Test
    void shouldAdd() {
        Comment comment = makeComment();

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }
    //should not add if rating is less than 1
    @Test
    void shouldNotAddRatingLessThanOne() {
        Comment comment = makeComment();
        comment.setRating(0);

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    //should not add if rating is greater than 5
    @Test
    void shouldNotAddRatingGreaterThan5() {
        Comment comment = makeComment();
        comment.setRating(6);

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    //should not add if description is blank
    @Test
    void shouldNotAddBlankDescription() {
        Comment comment = makeComment();
        comment.setDescription("");

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    //should not add if username is blank
    @Test
    void shouldNotAddBlankUsername() {
        Comment comment = makeComment();
        comment.setUsername("");

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    //should update
    @Test
    void shouldUpdate() {
        Comment comment = makeComment();
        comment.setCommentId(1);
        comment.setRating(2);

        Result<Comment> actual = service.update(comment);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }
    //should not update if commentId is 0
    @Test
    void shouldNotUpdateCommentIdZero() {
        Comment comment = makeComment();
        comment.setCommentId(0);

        Result<Comment> actual = service.update(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    //should not update if rating is less than 1
    @Test
    void shouldNotUpdateRatingLessThanOne() {
        Comment comment = makeComment();
        comment.setRating(0);

        Result<Comment> actual = service.update(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    //should not update if rating is greater than 5
    @Test
    void shouldNotUpdateRatingGreaterThan5() {
        Comment comment = makeComment();
        comment.setRating(6);

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    //should not update if description is blank
    @Test
    void shouldNotUpdateBlankDescription() {
        Comment comment = makeComment();
        comment.setDescription("");

        Result<Comment> actual = service.add(comment);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    //should delete
    @Test
    void shouldDelete() {
        Result<Comment> actual = service.deleteById(1);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    Comment makeComment() {
        Comment comment = new Comment();
        comment.setUsername("swagmaster9000");
        comment.setRating(4);
        comment.setDescription("Its pretty cool I guess...");
        comment.setEncounterId(1);
        return comment;
    }
}