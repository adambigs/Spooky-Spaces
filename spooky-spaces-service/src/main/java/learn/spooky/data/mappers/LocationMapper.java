package learn.spooky.data.mappers;

import learn.spooky.models.Location;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(resultSet.getInt("location_id"));
        location.setAddress(resultSet.getString("address"));
        location.setLatitude(resultSet.getString("latitude"));
        location.setLongitude(resultSet.getString("longitude"));
        location.setLocationName(resultSet.getString("location_name"));
        return location;
    }
}
