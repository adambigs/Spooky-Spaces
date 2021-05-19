package learn.spooky.models;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class Encounter {

    @Min(value = 0, message = "Encounter id must be a positive number greater than zero")
    private int encounterId;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 0, message = "Encounter id must be a positive number greater than zero")
    private int locationId;

    private EncounterType encounterType;

    private List<Comment> comment;

    public Encounter(){};

    public Encounter(@Min(value = 0, message = "Encounter id must be a positive number greater than zero") int encounterId,
                     @NotBlank(message = "Description is required") String description,
                     EncounterType encounterType, @Min(value = 0, message = "Encounter id must be a positive number greater than zero") int locationId) {
        this.encounterId = encounterId;
        this.description = description;
        this.encounterType = encounterType;
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EncounterType getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(EncounterType encounterType) {
        this.encounterType = encounterType;
    }

    public List<Comment> getComment() {
        return new ArrayList<>(comment);
    }

    public void setComment(List<Comment> comment) {
        this.comment = new ArrayList<>(comment);
    }
}