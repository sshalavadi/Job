package edu.gatech.seclass.jobcompare6300.entities.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import edu.gatech.seclass.jobcompare6300.entities.ComparisonSettings;

@Dao
public interface ComparisonSettingsDao {

    @Insert
    long insert(ComparisonSettings settings);

    @Update
    void update(ComparisonSettings settings);

    @Query("SELECT * FROM comparison_settings ORDER BY id LIMIT 1")
    ComparisonSettings load();
}
