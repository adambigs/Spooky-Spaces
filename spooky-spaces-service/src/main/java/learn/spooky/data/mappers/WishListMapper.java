package learn.spooky.data.mappers;

import learn.spooky.models.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishListMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet resultSet, int i) throws SQLException {
        WishList wishList = new WishList();
        wishList.setWishListId(resultSet.getInt("wishlist_id"));
        wishList.setUsername(resultSet.getString("username"));
        wishList.setLocationId(resultSet.getInt("location_id"));
        return wishList;
    }
}
