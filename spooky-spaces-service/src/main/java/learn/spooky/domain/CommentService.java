package learn.spooky.domain;


import learn.spooky.data.CommentRepository;
import learn.spooky.models.Comment;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }

    List<Comment> findByEncounter(int encounterId) {
        return repository.findByEncounter(encounterId);
    }

    public Result<Comment> add(Comment comment) {
        Result<Comment> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator(); // Returns a Hibernate validator hidden behind an interface.
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

        if (!violations.isEmpty()) { //check the validations from the model
            for (ConstraintViolation<Comment> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        result.setPayload(repository.add(comment));
        return result;
    }

    public Result<Comment> update(Comment comment){
        Result<Comment> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

        if (!violations.isEmpty()) { //check validations from the model
            for (ConstraintViolation<Comment> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        if (!repository.update(comment)) { //make sure the comment id exists
            String msg = String.format("Comment id %s is not found", comment.getCommentId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){ //update if successful
            repository.update(comment);
        }
        return result;
    }

    public Result<Comment> deleteById(int commentId) {
        Result<Comment> result = new Result<>();

        if (!repository.deleteById(commentId)) { //make sure the comment id exists
            String msg = String.format("Location id %s is not found", commentId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        if (result.isSuccess()){ //delete if successful
            repository.deleteById(commentId);
        }
        return result;
    }
}
