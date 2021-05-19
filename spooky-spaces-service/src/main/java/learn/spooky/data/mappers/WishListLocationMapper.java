package learn.spooky.data.mappers;

import learn.spooky.models.WishListLocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishListLocationMapper implements RowMapper<WishListLocation> {

    @Override
    public WishListLocation mapRow(ResultSet resultSet, int i) throws SQLException {
        WishListLocation wishListLocation = new WishListLocation();
        wishListLocation.setWishlistId(resultSet.getInt("wishlist_id"));

        LocationMapper locationMapper = new LocationMapper();
        wishListLocation.setLocation(locationMapper.mapRow(resultSet, i));

        return wishListLocation;
    }
}
