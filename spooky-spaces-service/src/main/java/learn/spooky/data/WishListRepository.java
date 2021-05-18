package learn.spooky.data;

import learn.spooky.models.WishList;

public interface WishListRepository {
    //Find by Id
    WishList findById(int wishListId);

    //Add
    WishList add(WishList wishList);

    //Update
    boolean update(WishList wishList);

    //Delete
    boolean deleteById(int wishListId);
}
