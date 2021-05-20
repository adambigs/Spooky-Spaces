package learn.spooky.data.mappers;

import learn.spooky.models.EncounterType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EncounterTypeMapper implements RowMapper<EncounterType> {

    @Override
    public EncounterType mapRow(ResultSet resultSet, int i) throws SQLException {
        return EncounterType.valueOfType(resultSet.getInt("type_id"));
    }
    
}
