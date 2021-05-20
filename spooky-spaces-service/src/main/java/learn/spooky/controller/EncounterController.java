package learn.spooky.controller;

import learn.spooky.domain.EncounterService;
import learn.spooky.domain.LocationService;
import learn.spooky.domain.Result;
import learn.spooky.models.Encounter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/encounter")
public class EncounterController {

    private final EncounterService service;

    public EncounterController(EncounterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Encounter> findAll(){
        return service.findAll();
    }

    @GetMapping("/{encounterId}")
    public ResponseEntity<Encounter> findById(@PathVariable int encounterId){
        Encounter encounter = service.findById(encounterId);
        if(encounter == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(encounter);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Encounter encounter){
        Result<Encounter> result = service.add(encounter);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorHandler.build(result);
    }

    @PutMapping
    public ResponseEntity<Object> update(@PathVariable int encounterId, @RequestBody Encounter encounter){
        if(encounterId != encounter.getEncounterId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Encounter> result = service.update(encounter);

        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }

    @DeleteMapping("/{encounterId}")
    public ResponseEntity<Object> deleteById(@PathVariable int encounterId){
        Result<Encounter> result = service.deleteById(encounterId);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorHandler.build(result);
    }
}
