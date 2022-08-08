package edu.gatech.seclass.jobcompare6300;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import java.security.InvalidParameterException;

import edu.gatech.seclass.jobcompare6300.entities.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.entities.dao.AppDatabase;
import edu.gatech.seclass.jobcompare6300.entities.dao.ComparisonSettingsDao;
import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;

public class App extends Application {

    private static final String CURRENT_JOB_KEY = "currentJob";
    public AppDatabase db;

    @Override
    public void onTerminate() {
        super.onTerminate();
        db.close();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Database initialization
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "jobcompare").allowMainThreadQueries().build();

        // If null, create the default ComparisonSettings in database
        ComparisonSettingsDao comparisonSettingsDao = db.comparisonSettingsDao();
        ComparisonSettings settings;
        settings = comparisonSettingsDao.load();
        if(settings==null) {
            settings = new ComparisonSettings();
            settings.id = comparisonSettingsDao.insert(settings);
        }
    }

    public void setCurrentJob(JobWithLocation job) {
        if(job!=null && job.jobOffer.id<=0) {
            throw new InvalidParameterException("JobOffer needs to have a valid ID");
        }

        SharedPreferences sharedPref = this.getSharedPreferences(CURRENT_JOB_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if( job == null ) {
            editor.putLong(CURRENT_JOB_KEY, 0);
        } else {
            editor.putLong(CURRENT_JOB_KEY, job.jobOffer.id);
        }
        editor.apply();
    }

    public JobWithLocation getCurrentJob() {
        SharedPreferences sharedPref = this.getSharedPreferences(CURRENT_JOB_KEY, Context.MODE_PRIVATE);
        long jobId = sharedPref.getLong(CURRENT_JOB_KEY,0);
        if( jobId <= 0 ) {
            return null;
        }

        JobWithLocation job = db.jobOfferDao().load(jobId);
        return job;
    }

}
