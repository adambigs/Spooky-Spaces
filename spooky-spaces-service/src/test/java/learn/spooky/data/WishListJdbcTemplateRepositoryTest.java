package learn.spooky.data;

import learn.spooky.models.Location;
import learn.spooky.models.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class WishListJdbcTemplateRepositoryTest {

    @Autowired
    WishListJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        WishList actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getWishListId());
        assertEquals("", actual.getUsername()); //put in a username
    }

    @Test
    void shouldNotFindInvalidId() {
        WishList actual = repository.findById(-1);
        assertNull(actual);
    }

    @Test
    void shouldAdd() {
        WishList wishList = makeWishList();
        WishList actual = repository.add(wishList);
        assertNotNull(actual);
        assertEquals(1, actual.getWishListId()); //change to whatever id should be
    }

    @Test
    void shouldUpdate() {
        WishList wishList = makeWishList();
        wishList.setWishListId(1);
        assertTrue(repository.update(wishList));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }

    WishList makeWishList() {
        WishList wishList = new WishList();
        wishList.setWishListId(1);
        wishList.setUsername("Debbie");
        return wishList;
    }

}
