package learn.spooky.data;

import learn.spooky.models.Location;
import learn.spooky.models.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void shouldFindAll() {
        List<WishList> all = repository.findAll();
        assertNotNull(all);
    }

    @Test
    void shouldFindByUsername() {
        List<WishList> actual = repository.findByUsername("cooldude69"); //put in valid name
        assertNotNull(actual);
    }

    @Test
    void shouldNotFindInvalidId() {
        List<WishList> actual = repository.findByUsername("username"); //put in invalid name
        assertEquals(0, actual.size());
    }

    @Test
    void shouldAdd() {
        WishList wishList = makeWishList();
        WishList actual = repository.add(wishList);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdate() {
        WishList wishList = makeWishList();
        wishList.setWishListId(1);
        assertTrue(repository.update(wishList));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteByUsername("swagmaster9000", 1));
        assertFalse(repository.deleteByUsername("swagmaster9000", 1));
    }

    WishList makeWishList() {
        WishList wishList = new WishList();
        wishList.setUsername("Debbie");
        wishList.setLocationId(1);
        return wishList;
    }
}
