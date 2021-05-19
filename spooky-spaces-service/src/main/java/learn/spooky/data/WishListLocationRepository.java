package learn.spooky.data;

import learn.spooky.models.WishListLocation;

public interface WishListLocationRepository {

    boolean add(WishListLocation wishListLocation);

    boolean delete(int wishListId, int locationId);
}
