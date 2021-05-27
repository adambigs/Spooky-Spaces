package learn.spooky.domain;

import learn.spooky.data.LocationRepository;
import learn.spooky.data.WishListRepository;
import learn.spooky.models.Location;
import learn.spooky.models.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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
    void shouldNotAddEmptyLocation() {
        WishList wishList = makeWishList();
        wishList.setLocationId(-1);

        Result<WishList> actual = service.add(wishList);
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
    void shouldNotDeleteInvalidUsername() {
        Result<WishList> actual = service.deleteByUsername("", 1);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotDeleteNonExistUsername() {
        Result<WishList> actual = service.deleteByUsername("username", 1); //something not in database
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    WishList makeWishList() {
        WishList wishList = new WishList();
        wishList.setWishListId(3);
        wishList.setUsername("Debbie");
        wishList.setLocationId(1);
        return wishList;
    }
}
