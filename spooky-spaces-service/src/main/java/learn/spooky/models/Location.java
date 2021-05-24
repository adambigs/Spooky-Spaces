package learn.spooky.models;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class Location {

    //basic validations, the variables cannot be null/empty
    @NotBlank(message = "Address is required")
    public String address;

    @NotBlank(message = "Latitude is required")
    public String latitude;

    @NotBlank(message = "Longitude is required")
    public String longitude;

    @NotBlank(message = "Location Name is required")
    public String locationName;

    @Min(value = 0, message = "Location id must be positive")
    public int locationId;

    public String locationImage;

    public List<Encounter> encounters = new ArrayList<>();;

    public Location() {
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(String locationImage) {
        this.locationImage = locationImage;
    }
}
