package learn.spooky.controller;

import learn.spooky.domain.LocationService;
import learn.spooky.domain.Result;
import learn.spooky.models.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Location> findAll() {
        return service.findAll();
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> findById(@PathVariable int locationId){
        Location location = service.findById(locationId);
        if (location == null) { //make sure there is a location with that id
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Location location) {
        Result<Location> result = service.add(location);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorHandler.build(result);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Object> update(@PathVariable int locationId, @RequestBody Location location) {
        if (locationId != location.getLocationId()) { //make sure location id exists
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Location> result = service.update(location);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }

//    @DeleteMapping("/{locationId}")
//    public ResponseEntity<Object> deleteById(@PathVariable int locationId) {
//        Result<Location> result = service.deleteById(locationId);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return ErrorHandler.build(result);
//    }
}
