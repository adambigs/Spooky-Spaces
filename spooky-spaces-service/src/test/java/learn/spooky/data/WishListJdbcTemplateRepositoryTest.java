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
        WishList wishList = makeWishList();
        repository.add(wishList);
        WishList actual = repository.findByUsername("Debbie");
        assertNotNull(actual);
        assertEquals("Debbie", actual.getUsername());
    }

    @Test
    void shouldNotFindInvalidId() {
        WishList actual = repository.findByUsername("username"); //put in invalid name
        assertNull(actual);
    }

    @Test
    void shouldAdd() {
        WishList wishList = makeWishList();
        WishList actual = repository.add(wishList);
        assertNotNull(actual);
        assertEquals(2, actual.getWishListId());
    }

    @Test
    void shouldUpdate() {
        WishList wishList = makeWishList();
        wishList.setWishListId(2);
        repository.add(wishList);
        wishList.setUsername("hello");
        assertEquals("hello", wishList.getUsername());
        assertTrue(repository.update(wishList));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteByUsername("cooldude69", 1));
        assertFalse(repository.deleteByUsername("cooldude69", 1));
    }

    WishList makeWishList() {
        WishList wishList = new WishList();
        wishList.setWishListId(2);
        wishList.setUsername("Debbie");
        return wishList;
    }
}
