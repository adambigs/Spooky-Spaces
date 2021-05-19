package learn.spooky.data;

import learn.spooky.models.Location;

import java.util.List;

public interface LocationRepository {


    //Find All
    List<Location> findAll();

    //Find by Id
    Location findById(int locationId);

    //Add
    Location add(Location location);

    //Update
    boolean update(Location location);

//    //Delete
//    boolean deleteById(int locationId);
}
