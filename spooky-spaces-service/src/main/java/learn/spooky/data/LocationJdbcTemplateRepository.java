package learn.spooky.data;

import learn.spooky.data.mappers.LocationMapper;
import learn.spooky.models.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocationJdbcTemplateRepository implements  LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Find All
    @Override
    public List<Location> findAll() {

        final String sql = "select location_id, address, latitude, longitude, location_name "
                + "from location;";

        return jdbcTemplate.query(sql, new LocationMapper());
    }

    //Find by Id
    @Override
    public Location findById(int locationId) {

        final String sql = "select location_id, address, latitude, longitude, location_name "
                + "from location "
                + "where location_id = ?;";

        return jdbcTemplate.query(sql, new LocationMapper(), locationId).stream()
                .findFirst()
                .orElse(null);
    }

    //Add
    @Override
    public Location add(Location location) {

        final String sql = "insert into location (address, latitude, longitude, location_name)"
                + "values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getAddress());
            ps.setString(2, location.getLatitude());
            ps.setString(3, location.getLongitude());
            ps.setString(4, location.getLocationName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    //Update
    @Override
    public boolean update(Location location) {

        // don't allow agency_id updates for now
        final String sql = "update location set "
                + "address = ?, "
                + "latitude = ?, "
                + "longitude = ?, "
                + "location_name = ? "
                + "where location_id = ?;";

        return jdbcTemplate.update(sql,
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationName(),
                location.getLocationId()) > 0;
    }

    //Delete
    @Override
    public boolean deleteById(int locationId) {
        return jdbcTemplate.update(
                "delete from location where location_id = ?", locationId) > 0;
    }
}
