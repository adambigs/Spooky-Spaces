package learn.spooky.data;

import learn.spooky.models.WishListLocation;

public class WishListLocationJdbcTemplateRepository implements WishListLocationRepository{

    @Override
    public boolean add(WishListLocation wishListLocation) {
        return false;
    }

    @Override
    public boolean delete(int wishListId, int locationId) {
        return false;
    }
}
