package learn.spooky.data.mappers;

import learn.spooky.models.Encounter;
import learn.spooky.models.EncounterType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EncounterMapper implements RowMapper<Encounter> {

    @Override
    public Encounter mapRow(ResultSet resultSet, int i) throws SQLException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(resultSet.getInt("encounter_id"));
        encounter.setDescription(resultSet.getString("encounter_description"));
        encounter.setLocationId(resultSet.getInt("location_id"));
        encounter.setEncounterType(EncounterType.valueOfType(resultSet.getInt("type.id")));
        return encounter;
    }

}
