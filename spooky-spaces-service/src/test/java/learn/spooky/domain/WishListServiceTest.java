package learn.spooky.domain;

import learn.spooky.data.LocationRepository;
import learn.spooky.data.WishListRepository;
import learn.spooky.models.Location;
import learn.spooky.models.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class WishListServiceTest {

    @Autowired
    WishListService service;

    @MockBean
    WishListRepository repository;

    @Test
    void shouldAdd(){
        WishList wishList = makeWishList();

        Result<WishList> actual = service.add(wishList);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotAddInvalidUsername(){
        WishList wishList = makeWishList();
        wishList.setUsername("");

        Result<WishList> actual = service.add(wishList);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddDuplicateUsername(){
        WishList wishList = makeWishList();
        wishList.setUsername("Debbie"); //something that is in the database

        Result<WishList> actual = service.add(wishList);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void ShouldUpdate(){
        WishList wishList = makeWishList();
        wishList.setWishListId(1);

        Result<WishList> actual = service.update(wishList);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void ShouldNotUpdateInvalidId(){
        WishList wishList = makeWishList();
        wishList.setWishListId(-1);

        Result<WishList> actual = service.update(wishList);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void ShouldNotUpdateNonExistId(){
        WishList wishList = makeWishList();
        wishList.setWishListId(100);

        Result<WishList> actual = service.update(wishList);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldDelete() {
        Result<WishList> actual = service.deleteByUsername("username");
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotDeleteInvalidUsername() {
        Result<WishList> actual = service.deleteByUsername("");
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotDeleteNonExistUsername() {
        Result<WishList> actual = service.deleteByUsername("username"); //something not in database
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    WishList makeWishList() {
        WishList wishList = new WishList();
        wishList.setWishListId(1);
        wishList.setUsername("Debbie");
        return wishList;
    }
}
