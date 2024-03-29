package learn.spooky.data;

import learn.spooky.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LocationJdbcTemplateRepositoryTest {

    @Autowired
    LocationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        Location actual = repository.findById(1);
        assertNotNull(actual);
        assertNotNull(actual.encounters);
    }

    @Test
    void shouldFindAll() {
        List<Location> actual = repository.findAll();
        assertNotNull(actual);
    }

    @Test
    void shouldAdd() {
        Location location = makeLocation();
        Location actual = repository.add(location);
        assertNotNull(actual);
        assertEquals(2, actual.getLocationId());
    }

    @Test
    void shouldUpdate() {
        Location location = makeLocation();
        location.setLocationId(1);
        assertTrue(repository.update(location));
    }

    Location makeLocation() {
        Location location = new Location();
        location.setLocationName("Haunted House");
        location.setAddress("somewhere in Milwaukee");
        location.setLatitude("1.234");
        location.setLongitude("-5.678");
        location.setLocationImage("Image.png");
        return location;
    }
}
