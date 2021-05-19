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
        assertEquals(2, all.size());
    }

    @Test
    void shouldFindByUsername() {
        WishList actual = repository.findByUsername("swagmaster9000"); //put in valid name
        assertNotNull(actual);
        assertEquals(2, actual.getWishListId());
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
        assertEquals(3, actual.getWishListId()); //change to whatever id should be
    }

    @Test
    void shouldUpdate() {
        WishList wishList = makeWishList();
        wishList.setWishListId(1);
        assertTrue(repository.update(wishList));
    }

//    @Test
//    void shouldDelete() {
//        assertTrue(repository.deleteByUsername("swagmaster9000"));
//        assertFalse(repository.deleteByUsername("swagmaster9000"));
//    }

    WishList makeWishList() {
        WishList wishList = new WishList();
        wishList.setWishListId(3);
        wishList.setUsername("Debbie");
        return wishList;
    }
}
