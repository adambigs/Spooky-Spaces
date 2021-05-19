package learn.spooky.data;

import learn.spooky.models.WishListLocation;

public interface WishListLocationRepository {

    <List>WishListLocation findByWishListId(int wishlistId);

    WishListLocation add(WishListLocation wishListLocation);

    boolean delete(int wishListId, int locationId);
}
