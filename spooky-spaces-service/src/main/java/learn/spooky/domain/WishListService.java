package learn.spooky.domain;

import learn.spooky.data.WishListRepository;
import learn.spooky.models.WishList;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class WishListService {

    private final WishListRepository repository;

    public WishListService(WishListRepository repository) {
        this.repository = repository;
    }

    //find All
    public List<WishList> findAll(){
        return repository.findAll();
    }

    //find by username
    // validation done in the controller
    public WishList findByUsername(String username) {return  repository.findByUsername(username);}

    //add
    public Result<WishList> add(WishList wishList) {
        Result<WishList> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator(); // Returns a Hibernate validator hidden behind an interface.
        Set<ConstraintViolation<WishList>> violations = validator.validate(wishList);

        if (!violations.isEmpty()) { //check the validations from the model
            for (ConstraintViolation<WishList> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        if (violations.isEmpty()){ //check for duplicates
            List<WishList> wishLists = findAll();
            for (WishList l : wishLists){
                if (Objects.equals(l.getUsername(), wishList.getUsername())){ //check username
                    result.addMessage("Duplicate WishList not allowed", ResultType.INVALID);
                    return result;
                }
            }
        }
        result.setPayload(repository.add(wishList));
        return result;
    }

    //Update
    public Result<WishList> update(WishList wishList){
        Result<WishList> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<WishList>> violations = validator.validate(wishList);

        if (!violations.isEmpty()) { //check validations from the model
            for (ConstraintViolation<WishList> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        if (!repository.update(wishList)) { //make sure the wishList id exists
            String msg = String.format("Wish List id %s is not found", wishList.getWishListId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){ //update if successful
            repository.update(wishList);
        }

        return result;
    }

    //Delete
    public Result<WishList> deleteByUsername(String username) {
        Result<WishList> result = new Result<>();

        if (!repository.deleteByUsername(username)) { //make sure the wishList id exists
            String msg = String.format("Wish List username %s is not found", username);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){ //update if successful
            repository.deleteByUsername(username);
        }
        return result;
    }
}
