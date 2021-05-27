package learn.spooky.data;

import learn.spooky.models.Comment;
import learn.spooky.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CommentJdbcTemplateRepositoryTest {

    @Autowired
    CommentJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll(){
        List<Comment> actual = repository.findAll();
        assertNotNull(actual);
    }

    @Test
    void shouldFindByEncounter(){
        List<Comment> comment = repository.findByEncounter(1);
        assertNotNull(comment);
        assertTrue(comment.size() > 0);
    }

    @Test
    void shouldNotFindByEncounter(){
        List<Comment> comment = repository.findByEncounter(1000);
        assertFalse(comment.size() > 0);
    }

    @Test
    void shouldFindById(){
        Comment comment = repository.findById(1);
        assertNotNull(comment);
        assertEquals("cooldude69", comment.getUsername());
    }

    @Test
    void shouldNotFindById(){
        Comment comment = repository.findById(1000);
        assertNull(comment);
    }

    @Test
    void shouldAdd(){
        Comment comment = makeComment();
        Comment actual = repository.add(comment);
        assertNotNull(actual);
        assertEquals("swagmaster9000", actual.getUsername());
    }

    @Test
    void shouldNotAddInvalidUsername(){
        Comment comment = makeInvalidUsernameComment();
        Comment actual = repository.add(comment);
        assertEquals("", actual.getUsername());
    }

    @Test
    void shouldNotAddInvalidRating(){
        Comment comment = makeInvalidCommentRating();
        Comment actual = repository.add(comment);
        assertFalse(actual.getRating() > 0);
    }

    @Test
    void shouldNotAddInvalidDescription(){
        Comment comment = makeInvalidCommentDescription();
        Comment actual = repository.add(comment);
        assertEquals("", actual.getDescription());
    }

    @Test
    void shouldUpdate(){
        Comment comment = makeComment();
        comment.setCommentId(1);
        assertTrue(repository.update(comment));
    }

    @Test
    void shouldNotUpdate(){
        Comment comment = makeComment();
        comment.setCommentId(0);
        assertFalse(repository.update(comment));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(1));
    }

    @Test
    void shouldNotDelete(){
        assertFalse(repository.deleteById(300));
    }

    Comment makeComment() {
        Comment comment = new Comment();
        comment.setUsername("swagmaster9000");
        comment.setRating(4);
        comment.setDescription("Its pretty cool I guess...");
        comment.setEncounterId(1);
        return comment;
    }

    Comment makeInvalidUsernameComment() {
        Comment comment = new Comment();
        comment.setUsername("");
        comment.setRating(4);
        comment.setDescription("Its pretty cool I guess...");
        comment.setEncounterId(1);
        return comment;
    }

    Comment makeInvalidCommentRating() {
        Comment comment = new Comment();
        comment.setUsername("swagmaster9000");
        comment.setRating(0);
        comment.setDescription("Its pretty cool I guess...");
        comment.setEncounterId(1);
        return comment;
    }

    Comment makeInvalidCommentDescription() {
        Comment comment = new Comment();
        comment.setUsername("swagmaster9000");
        comment.setRating(5);
        comment.setDescription("");
        comment.setEncounterId(1);
        return comment;
    }

}