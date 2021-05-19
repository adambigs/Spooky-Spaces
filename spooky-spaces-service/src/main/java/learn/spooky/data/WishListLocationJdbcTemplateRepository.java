package learn.spooky.data;

import learn.spooky.models.WishListLocation;

public class WishListLocationJdbcTemplateRepository implements WishListLocationRepository{

    @Override
    public <List> WishListLocation findByWishListId(int wishlistId) {
        return null;
    }

    @Override
    public WishListLocation add(WishListLocation wishListLocation) {
        return wishListLocation;
    }

    @Override
    public boolean delete(int wishListId, int locationId) {
        return false;
    }
}
