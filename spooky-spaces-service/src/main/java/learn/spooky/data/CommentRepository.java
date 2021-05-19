package learn.spooky.data;

import learn.spooky.models.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAll();

    List<Comment> findByEncounter(int encounterId);

    @Transactional
    Comment findById(int commentId);

    Comment add(Comment comment);

    boolean update(Comment comment);

    @Transactional
    boolean deleteById(int commentId);

}
