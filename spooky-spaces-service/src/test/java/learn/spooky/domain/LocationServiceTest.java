package learn.spooky.domain;

import learn.spooky.data.LocationRepository;
import learn.spooky.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LocationServiceTest {

    @Autowired
    LocationService service;

    @MockBean
    LocationRepository repository;

    @Test
    void shouldAdd() {
        Location location = makeLocation();

        Result<Location> actual = service.add(location);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void ShouldNotAddInvalidName(){
        Location location = makeLocation();
        location.setLocationName("");

        Result<Location> actual = service.add(location);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void ShouldNotAddInvalidAddress(){
        Location location = makeLocation();
        location.setAddress("");

        Result<Location> actual = service.add(location);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void ShouldNotAddInvalidLat(){
        Location location = makeLocation();
        location.setLatitude("");

        Result<Location> actual = service.add(location);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void ShouldNotAddInvalidLong(){
        Location location = makeLocation();
        location.setLongitude("");

        Result<Location> actual = service.add(location);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateInvalidId(){
        Location location = makeLocation();
        location.setLocationId(-1);

        Result<Location> actual = service.update(location);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateNonExistId(){
        Location location = makeLocation();
        location.setLocationId(100);

        Result<Location> actual = service.update(location);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

//    @Test
//    void shouldDelete() {
//        Result<Location> actual = service.deleteById(1);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//    }
//
//    @Test
//    void shouldNotDeleteInvalid() {
//        Result<Location> actual = service.deleteById(-1);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//    @Test
//    void shouldNotDeleteNonExistId() {
//        Result<Location> actual = service.deleteById(100);
//        assertEquals(ResultType.NOT_FOUND, actual.getType());
//    }

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
