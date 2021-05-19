package learn.spooky.data;

import learn.spooky.data.mappers.CommentMapper;
import learn.spooky.data.mappers.EncounterMapper;
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
public class EncounterJdbcTemplateRepository implements EncounterRepository {

    private final JdbcTemplate jdbcTemplate;

    public EncounterJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Encounter> findAll() {
        final String sql = "select encounter_id, " +
                "encounter_description, " +
                "location_id, type_id" +
                " from encounter;";

        return jdbcTemplate.query(sql, new EncounterMapper());
    }

    @Override
    @Transactional
    public Encounter findById(int encounterId) {
        final String sql = "select encounter_id, " +
                "encounter_description, " +
                "location_id, type_id" +
                " from encounter " +
                "where encounter_id = ?;";

        Encounter result = jdbcTemplate.query(sql, new EncounterMapper(), encounterId).stream()
                .findAny().orElse(null);

        if(result != null){
            addComments(result);
        }

        return result;
    }

    @Override
    public Encounter add(Encounter encounter) {
        final String sql = "insert into encounter " +
                "(encounter_description, location_id, type_id) " +
                "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsEffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, encounter.getDescription());
            ps.setInt(2, encounter.getLocationId());
            ps.setInt(3, encounter.getEncounterType().getTypeId());
            return ps;
            }, keyHolder);

        if(rowsEffected <= 0) {
            return null;
        }

        encounter.setEncounterId(keyHolder.getKey().intValue());
        return encounter;
    }

    @Override
    public boolean update(Encounter encounter) {
        final String sql = "update encounter set " +
                "encounter_description = ?, " +
                "location_id = ?, " +
                "type_id = ?, " +
                "where encounter_id = ?;";

        return jdbcTemplate.update(sql,
                encounter.getDescription(),
                encounter.getLocationId(),
                encounter.getEncounterType(),
                encounter.getEncounterId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int encounterId) {
        jdbcTemplate.update("delete from comments where encounter_id = ?", encounterId);
        return jdbcTemplate.update("delete from encounter where encounter_id = ?", encounterId) > 0;
    }

    private void addComments(Encounter encounter){

        final String sql = "select " +
                "comment_id, " +
                "username, " +
                "rating, " +
                "comment_text, " +
                "encounter_id " +
                "from comments" +
                "where encounter_id = ?;";

        var comments = jdbcTemplate.query(sql, new CommentMapper(), encounter.getEncounterId());
        encounter.setComment(comments);
    }

//    private void addEncounterTypes(Encounter encounter){
//
//        final String sql = "";
//
//
//
//    }
}
