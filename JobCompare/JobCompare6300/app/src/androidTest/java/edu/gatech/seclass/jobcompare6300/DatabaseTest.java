package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.entities.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.entities.JobOffer;
import edu.gatech.seclass.jobcompare6300.entities.Location;
import edu.gatech.seclass.jobcompare6300.entities.dao.AppDatabase;
import edu.gatech.seclass.jobcompare6300.entities.dao.ComparisonSettingsDao;
import edu.gatech.seclass.jobcompare6300.entities.dao.JobOfferDao;
import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;
import edu.gatech.seclass.jobcompare6300.entities.dao.LocationDao;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private AppDatabase db;
    private LocationDao locationDao;
    private JobOfferDao jobOfferDao;
    private ComparisonSettingsDao settingsDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        locationDao = db.locationDao();
        jobOfferDao = db.jobOfferDao();
        settingsDao = db.comparisonSettingsDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void writeLocation() throws Exception {
        Location location = new Location();
        location.setCity("San Francisco");
        location.setState("CA");
        location.setLivingCostIndex(200);
        location.id = locationDao.insert(location);

        assertNotEquals( 0, location.id);

        List<Location> byCity = locationDao.findByCity("San Francisco", "CA");
        assertEquals(byCity.get(0).getCity(),location.getCity());
        assertEquals(byCity.get(0).getState(),location.getState());
        assertEquals(byCity.get(0).getLivingCostIndex(),location.getLivingCostIndex());

        assertNotEquals( 0, byCity.get(0).id);
    }

    @Test
    public void writeComparisonSettings() throws Exception {
        ComparisonSettings settings = settingsDao.load();
        assertEquals(null, settings);

        if( settings == null ) {
            settings = new ComparisonSettings();
            settings.id = settingsDao.insert(settings);

            assertNotEquals(0, settings.id);
        }

        settings.setYearlySalaryWeight(2);
        settings.setRelocationStipendWeight(2);

        settingsDao.update(settings);

        long oldId = settings.id;
        settings = settingsDao.load();
        assertNotEquals(null, settings);
        assertEquals(oldId,settings.id);
        assertEquals(2, settings.getYearlySalaryWeight());
        assertEquals(2, settings.getRelocationStipendWeight());
    }

    @Test
    public void writeJobOffer() throws Exception {
        double salary = 1234.57;
        double bonus = 890.12;
        double retirement = 50.0;
        double relocation = 56.34;
        double training = 345.76;

        Location location = new Location();
        location.setCity("San Francisco");
        location.setState("CA");
        location.setLivingCostIndex(200);
        location.id = locationDao.insert(location);

        JobWithLocation job = new JobWithLocation();

        job.jobOffer.setYearlySalary(salary);
        job.jobOffer.setYearlyBonus(bonus);
        job.jobOffer.setRetirementBenefits(retirement);
        job.jobOffer.setRelocationStipend(relocation);
        job.jobOffer.setTrainingFund(training);

        job.setLocation(location);

        ComparisonSettings settings = new ComparisonSettings();

        job.calculateJobScore(settings);

        long jobId = jobOfferDao.insert(job.jobOffer);
        assertNotEquals( 0, jobId);

        List<JobWithLocation> jobOfferList = jobOfferDao.getAll();

        assertNotEquals(0, jobOfferList.size());

        if( jobOfferList.size() > 0 ) {
            assertEquals(jobOfferList.get(0).getLocation().getCity(),job.getLocation().getCity());
            assertEquals(jobOfferList.get(0).getLocation().getState(),job.getLocation().getState());
            assertEquals(jobOfferList.get(0).getLocation().getLivingCostIndex(),job.getLocation().getLivingCostIndex());
        }
    }
    @Test
    public void deleteJobOffer() throws Exception {
        double salary = 1234.57;
        double bonus = 890.12;
        double retirement = 50.0;
        double relocation = 56.34;
        double training = 345.76;

        Location location = new Location();
        location.setCity("San Francisco");
        location.setState("CA");
        location.setLivingCostIndex(200);
        location.id = locationDao.insert(location);

        JobWithLocation job = new JobWithLocation();

        job.jobOffer.setYearlySalary(salary);
        job.jobOffer.setYearlyBonus(bonus);
        job.jobOffer.setRetirementBenefits(retirement);
        job.jobOffer.setRelocationStipend(relocation);
        job.jobOffer.setTrainingFund(training);

        job.setLocation(location);

        ComparisonSettings settings = new ComparisonSettings();

        job.calculateJobScore(settings);

        long jobId = jobOfferDao.insert(job.jobOffer);
        assertNotEquals(0, jobId);
        job.jobOffer.id = jobId;
        jobOfferDao.delete(job.jobOffer);
        List<JobWithLocation> jobOfferList = jobOfferDao.getAll();
        assertEquals(0, jobOfferList.size());
    }
}
