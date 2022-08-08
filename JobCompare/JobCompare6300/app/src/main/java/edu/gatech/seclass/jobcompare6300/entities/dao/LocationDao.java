package edu.gatech.seclass.jobcompare6300.entities.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.entities.Location;

@Dao
public interface LocationDao {
    @Insert
    long insert(Location location);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);

    @Query("SELECT * FROM locations")
    List<Location> getAll();

    @Query("SELECT * FROM locations WHERE city = :city AND state = :state")
    List<Location> findByCity(String city, String state);

    @Query("SELECT * FROM locations WHERE id = :id")
    Location load(long id);
}
