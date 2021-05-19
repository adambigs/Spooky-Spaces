package learn.spooky.data;

import learn.spooky.models.WishList;

import java.util.List;

public interface WishListRepository {

    //Find All
    List<WishList> findAll();

    //Find by Id
    WishList findByUsername(String username);

    //Add
    WishList add(WishList wishList);

    //Update
    boolean update(WishList wishList);

    //Delete
    boolean deleteByUsername(String username);
}
