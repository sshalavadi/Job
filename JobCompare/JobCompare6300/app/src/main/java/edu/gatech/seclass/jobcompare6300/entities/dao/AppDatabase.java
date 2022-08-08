package edu.gatech.seclass.jobcompare6300.entities.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import edu.gatech.seclass.jobcompare6300.entities.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.entities.JobOffer;
import edu.gatech.seclass.jobcompare6300.entities.Location;

@Database(entities = {Location.class, JobOffer.class, ComparisonSettings.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
    public abstract JobOfferDao jobOfferDao();
    public abstract ComparisonSettingsDao comparisonSettingsDao();
}
