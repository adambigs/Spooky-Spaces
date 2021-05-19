package learn.spooky.data.mappers;

import learn.spooky.models.Comment;
import learn.spooky.models.Encounter;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt("comment_id"));
        comment.setUsername(resultSet.getString("username"));
        comment.setRating(resultSet.getInt("rating"));
        comment.setDescription(resultSet.getString("comment_text"));
        comment.setEncounterId(resultSet.getInt("encounter_id"));
        return comment;
    }
}
