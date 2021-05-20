package learn.spooky.data;

import learn.spooky.models.Comment;
import learn.spooky.models.Encounter;
import learn.spooky.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EncounterJdbcTemplateRepositoryTest {

    @Autowired
    EncounterJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAll(){
        List<Encounter> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldFindById(){
        Encounter encounter = repository.findById(1);
        assertNotNull(encounter);
        assertEquals(1, encounter.getEncounterId());
    }

    @Test
    void shouldNotFindById(){
        Encounter encounter = repository.findById(0);
        assertNull(encounter);
    }

    @Test
    void shouldAdd(){
        Encounter encounter = makeEncounter();
        repository.add(encounter);
        assertNotNull(encounter);
        assertEquals("Haunted broom closet", encounter.getDescription());
    }

    @Test
    void shouldNotAddInvalidDescription(){
        Encounter encounter = makeInvalidEncounterDescription();
        repository.add(encounter);
        assertEquals("", encounter.getDescription());
    }

    @Test
    void shouldUpdate(){
        Encounter encounter = makeEncounter();
        encounter.setEncounterId(1);
        assertTrue(repository.update(encounter));
    }

    @Test
    void shouldNotUpdate(){
        Encounter encounter = makeEncounter();
        encounter.setEncounterId(0);
        assertFalse(repository.update(encounter));
    }

    @Test
    void shouldDelete(){
        assertTrue(repository.deleteById(2)); //find real id in test database
    }

    @Test
    void shouldNotDelete(){
        assertFalse(repository.deleteById(0)); //find real id in test database
    }

    Encounter makeEncounter(){
        Encounter encounter = new Encounter();
        encounter.setDescription("Haunted broom closet");
        encounter.setLocationId(1);
        return encounter;
    }

    Encounter makeInvalidEncounterDescription(){
        Encounter encounter = new Encounter();
        encounter.setDescription("");
        encounter.setLocationId(1);
        return encounter;
    }

}