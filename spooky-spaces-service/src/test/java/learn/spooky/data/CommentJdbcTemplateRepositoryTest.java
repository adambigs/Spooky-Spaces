package learn.spooky.data;

import learn.spooky.models.Comment;
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
        assertEquals(1, actual.size()); //replace with amount from test database
    }

    @Test
    void shouldFindByEncounter(){
        List<Comment> comment = repository.findByEncounter(1);
        assertNotNull(comment);
        assertEquals(19, comment.size()); // change to test database examples
    }

    @Test
    void shouldNotFindByEncounter(){
        List<Comment> comment = repository.findByEncounter(1000);
        assertNull(comment);
    }

    @Test
    void shouldFindById(){
        Comment comment = repository.findById(1);
        assertNotNull(comment);
        assertEquals("swagMaster9000", comment.getUsername());
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
        assertEquals("swagMaster9000", actual.getUsername());
    }

    @Test
    void shouldNotAddInvalidUsername(){
        Comment comment = makeInvalidUsernameComment();
        Comment actual = repository.add(comment);
        assertNull(actual);
    }

    @Test
    void shouldNotAddInvalidRating(){
        Comment comment = makeInvalidCommentRating();
        Comment actual = repository.add(comment);
        assertNull(actual);
    }

    @Test
    void shouldNotAddInvalidDescription(){
        Comment comment = makeInvalidCommentDescription();
        Comment actual = repository.add(comment);
        assertNull(actual);
    }

    @Test
    void shouldUpdate(){
        Comment comment = makeComment();
        comment.setCommentId(4); //insert real id number here
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
        assertTrue(repository.deleteById(3));
    }

    @Test
    void shouldNotDelete(){
        assertFalse(repository.deleteById(300));
    }

    Comment makeComment() {
        Comment comment = new Comment();
        comment.setUsername("swagMaster9000");
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
        comment.setUsername("swagMaster9000");
        comment.setRating(0);
        comment.setDescription("Its pretty cool I guess...");
        comment.setEncounterId(1);
        return comment;
    }

    Comment makeInvalidCommentDescription() {
        Comment comment = new Comment();
        comment.setUsername("swagMaster9000");
        comment.setRating(5);
        comment.setDescription("");
        comment.setEncounterId(1);
        return comment;
    }

}