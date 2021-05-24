package learn.spooky.data;

import learn.spooky.data.mappers.EncounterMapper;
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

    //Find All locations
    @Override
    public List<Location> findAll() {

        final String sql = "select location_id, location_name, address, latitude, longitude, image "
                + "from location;";

        return jdbcTemplate.query(sql, new LocationMapper());
    }

    //Find location by id
    //Might be helpful to find by name?
    @Override
    public Location findById(int locationId) {

        final String sql = "select location_id, location_name, address, latitude, longitude, image "
                + "from location "
                + "where location_id = ?;";



        Location result = jdbcTemplate.query(sql, new LocationMapper(), locationId).stream()
                .findFirst()
                .orElse(null);

        if(result != null){
            addEncounters(result);
        }

        return result;
    }

    //Add a new location to the database
    @Override
    public Location add(Location location) {

        final String sql = "insert into location (location_name, address, latitude, longitude, image)"
                + "values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getLocationName());
            ps.setString(2, location.getAddress());
            ps.setString(3, location.getLatitude());
            ps.setString(4, location.getLongitude());
            ps.setString(5, location.getLocationImage());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    //Update info for a location
    @Override
    public boolean update(Location location) {

        // don't allow agency_id updates for now
        final String sql = "update location set "
                + "location_name = ?, "
                + "address = ?, "
                + "latitude = ?, "
                + "longitude = ?, "
                + "image = ? "
                + "where location_id = ?;";

        return jdbcTemplate.update(sql,
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationName(),
                location.getLocationImage(),
                location.getLocationId()) > 0;
    }

    private void addEncounters(Location location){

        final String sql = "select " +
                "encounter_id, " +
                "encounter_description, " +
                "location_id " +
                "from encounter " +
                "where location_id = ?;";

        var encounters = jdbcTemplate.query(sql, new EncounterMapper(), location.getLocationId());
        location.setEncounters(encounters);
    }

    //Delete a location
    //This may case conflicts, probably needs to be deleted from the encounters table first
//    @Override
//    public boolean deleteById(int locationId) {
//        return jdbcTemplate.update(
//                "delete from location where location_id = ?", locationId) > 0;
//    }
}
