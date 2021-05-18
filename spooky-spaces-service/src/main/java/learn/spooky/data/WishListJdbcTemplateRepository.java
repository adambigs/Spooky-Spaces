package learn.spooky.data;

import learn.spooky.data.mappers.WishListMapper;
import learn.spooky.models.WishList;
import learn.spooky.models.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class WishListJdbcTemplateRepository implements WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Find by Id
    @Override
    public WishList findById(int wishListId) {

        final String sql = "select wishlist_id, username "
                + "from wishlist "
                + "where wishlist_id = ?;";

        return jdbcTemplate.query(sql, new WishListMapper(), wishListId).stream()
                .findFirst()
                .orElse(null);
    }

    //Add
    @Override
    public WishList add(WishList wishList) {

        final String sql = "insert into wishlist (username)"
                + "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishList.getUsername());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        wishList.setWishListId(keyHolder.getKey().intValue());
        return wishList;
    }

    //Update
    @Override
    public boolean update(WishList wishList) {

        // don't allow agency_id updates for now
        final String sql = "update wishlist set "
                + "username = ? "
                + "where wishlist_id = ?;";

        return jdbcTemplate.update(sql,
                wishList.getUsername(),
                wishList.getWishListId()) > 0;
    }

    //Delete
    @Override
    public boolean deleteById(int wishListId) {
        return jdbcTemplate.update(
                "delete from wishlist where wishlist_id = ?", wishListId) > 0;
    }

}
