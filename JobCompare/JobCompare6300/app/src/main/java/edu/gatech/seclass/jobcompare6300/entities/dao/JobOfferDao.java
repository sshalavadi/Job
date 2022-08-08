package edu.gatech.seclass.jobcompare6300.entities.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.entities.JobOffer;
import edu.gatech.seclass.jobcompare6300.entities.Location;

@Dao
public interface JobOfferDao {
    @Insert
    long insert(JobOffer jobOffer);

    @Delete
    void delete(JobOffer jobOffer);

    @Update
    void update(JobOffer jobOffer);

    @Query("SELECT * FROM job_offers")
    List<JobOffer> getNoLocation();

    @Transaction
    @Query("SELECT * FROM job_offers ORDER BY jobScore DESC")
    List<JobWithLocation> getAll();

    @Transaction
    @Query("SELECT * FROM job_offers WHERE id = :id")
    JobWithLocation load(long id);
}
