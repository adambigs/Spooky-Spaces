package learn.spooky.domain;

import learn.spooky.data.EncounterRepository;
import learn.spooky.models.Encounter;
import learn.spooky.models.EncounterType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EncounterServiceTest {

    @Autowired
    EncounterService service;

    @MockBean
    EncounterRepository repository;

    @Test
    void shouldAdd(){
        Encounter encounter = makeEncounter();
        Encounter mockOut = makeEncounter();

        mockOut.setEncounterId(1);

        when(repository.add(encounter)).thenReturn(mockOut);

        Result<Encounter> actual = service.add(encounter);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddEmptyDescription(){
        Encounter encounter = makeEncounter();
        encounter.setDescription("");

        Result<Encounter> actual = service.add(encounter);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddInvalidLocation(){
        Encounter encounter = makeEncounter();
        encounter.setLocationId(0);

        Result<Encounter> actual = service.add(encounter);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate(){
        Encounter encounter = makeEncounter();
        encounter.setEncounterId(1);

        when(repository.update(encounter)).thenReturn(true);

        Result<Encounter> actual = service.update(encounter);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateEmptyDescription(){
        Encounter encounter = makeEncounter();
        encounter.setEncounterId(1);
        encounter.setDescription("");

        when(repository.update(encounter)).thenReturn(true);

        Result<Encounter> actual = service.update(encounter);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotUpdateInvalidLocation(){
        Encounter encounter = makeEncounter();
        encounter.setEncounterId(1);
        encounter.setLocationId(0);

        when(repository.update(encounter)).thenReturn(true);

        Result<Encounter> actual = service.update(encounter);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldDelete(){
        when(repository.deleteById(2)).thenReturn(true);
        assertEquals(ResultType.SUCCESS ,service.deleteById(2).getType());
    }

    @Test
    void shouldNotDeleteByInvalidId(){
        when(repository.deleteById(-1)).thenReturn(false);
        assertEquals(ResultType.NOT_FOUND, service.deleteById(-1).getType());
    }

    Encounter makeEncounter(){
        Encounter encounter = new Encounter();
        encounter.setDescription("Haunted broom closet");
        encounter.setLocationId(1);
        encounter.setEncounterType(EncounterType.TOUCH);
        return encounter;
    }
}
