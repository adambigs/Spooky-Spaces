package learn.spooky.data;

import learn.spooky.data.mappers.CommentMapper;
import learn.spooky.data.mappers.EncounterMapper;
import learn.spooky.models.Comment;
import learn.spooky.models.Encounter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CommentJdbcTemplateRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findAll() {
        final String sql = "select comment_id, " +
                "username, " +
                "rating, " +
                "comment_text " +
                "from comments;";

        return jdbcTemplate.query(sql, new CommentMapper());
    }

    @Override
    public List<Comment> findByEncounter(int encounterId) {
        final String sql = "select comment_id, " +
                "username, " +
                "rating, " +
                "comment_text " +
                "encounter_id" +
                "from comments " +
                "where e.encounter_id = ?;";

        return jdbcTemplate.query(sql, new CommentMapper());
    }

    @Override
    @Transactional
    public Comment findById(int commentId) {
        final String sql = "select comment_id, " +
                "username, " +
                "rating, " +
                "comment_text " +
                "encounter_id " +
                "from comments " +
                "where comment_id = ?;";

        Comment result = jdbcTemplate.query(sql, new CommentMapper(), commentId).stream()
                .findAny().orElse(null);

        return result;
    }

    @Override
    public Comment add(Comment comment) {
        final String sql = "insert into comments " +
                "(username, rating, comment_text, encounter_id) " +
                "values (?,?,?,?);";


        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsEffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comment.getUsername());
            ps.setInt(2,comment.getRating());
            ps.setString(3, comment.getDescription());
            ps.setInt(4, comment.getEncounterId());
            return ps;
        }, keyHolder);

        if(rowsEffected <= 0) {
            return null;
        }

        comment.setCommentId(keyHolder.getKey().intValue());
        return comment;
    }

    @Override
    public boolean update(Comment comment) {

        final String sql = "update comment set " +
                "username = ?, " +
                "rating = ?, " +
                "comment_text = ?, " +
                "encounter_id = ? " +
                "where comment_id = ?;";

        return jdbcTemplate.update(sql,
                comment.getUsername(),
                comment.getRating(),
                comment.getDescription(),
                comment.getEncounterId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int commentId) {
        return jdbcTemplate.update("delete from comments where comment_id = ?;") > 0;
    }

}
