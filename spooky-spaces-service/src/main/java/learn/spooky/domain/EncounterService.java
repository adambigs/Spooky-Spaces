package learn.spooky.domain;

import learn.spooky.data.EncounterRepository;
import learn.spooky.models.Encounter;
import learn.spooky.models.Location;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository){this.repository = repository;}

    public List<Encounter> findAll(){
        return repository.findAll();
    }

    public Encounter findById(int encounterId){
        return repository.findById(encounterId);
    }

    public Result<Encounter> add(Encounter encounter){
        Result<Encounter> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Encounter>> violations = validator.validate(encounter);

        if(!violations.isEmpty()){
            for(ConstraintViolation<Encounter> violation : violations){
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        result.setPayload(repository.add(encounter));
        return result;
    }

    public Result<Encounter> update(Encounter encounter){
        Result<Encounter> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Encounter>> violations = validator.validate(encounter);


        if(!violations.isEmpty()){
            for(ConstraintViolation<Encounter> violation : violations){
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }

        if(!repository.update(encounter)){
            String msg = String.format("Encounter ID: %s not found", encounter.getEncounterId());
        }

        return result;
    }

    public boolean deleteById(int encounterId){
        return repository.deleteById(encounterId);
    }

}
