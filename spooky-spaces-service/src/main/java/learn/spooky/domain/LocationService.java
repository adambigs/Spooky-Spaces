package learn.spooky.domain;

import learn.spooky.data.LocationRepository;
import learn.spooky.models.Location;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    //Find All
    public List<Location> findAll(){
        return repository.findAll();
    }

    //Add
    public Result<Location> add(Location location) {
        Result<Location> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator(); // Returns a Hibernate validator hidden behind an interface.
        Set<ConstraintViolation<Location>> violations = validator.validate(location);

        if (!violations.isEmpty()) { //check the validations from the model
            for (ConstraintViolation<Location> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        if (violations.isEmpty()){ //check for duplicates
            List<Location> locations = findAll();
            for (Location l : locations){
                if (Objects.equals(l.getLocationName(), location.getLocationName()) // if the name is the same
                    || ((Objects.equals(l.getLatitude(), location.getLatitude())) //if the lat and long are the same
                    && Objects.equals(l.getLongitude(), location.getLongitude()))){
                    result.addMessage("Duplicate Location not allowed", ResultType.INVALID);
                    return result;
                }
            }
        }

        result.setPayload(repository.add(location));
        return result;
    }

    //Update
    public Result<Location> update(Location location){
        Result<Location> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Location>> violations = validator.validate(location);

        if (!violations.isEmpty()) { //check validations from the model
            for (ConstraintViolation<Location> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        if (!repository.update(location)) { //make sure the location id exists
            String msg = String.format("Location id %s is not found", location.getLocationId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){ //update if successful
            repository.update(location);
        }

        return result;
    }

    //Delete
    public Result<Location> deleteById(int locationId) {
        Result<Location> result = new Result<>();

        if (!repository.deleteById(locationId)) { //make sure the location id exists
            String msg = String.format("Location id %s is not found", locationId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){ //update if successful
            repository.deleteById(locationId);
        }
        return result;
    }
}
