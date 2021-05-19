package learn.spooky.data;

import learn.spooky.models.Encounter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EncounterRepository {
    List<Encounter> findAll();

    @Transactional
    Encounter findById(int encounterId);

    Encounter add(Encounter encounter);

    boolean update(Encounter encounter);

    @Transactional
    boolean deleteById(int encounterId);

}
