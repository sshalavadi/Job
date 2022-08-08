package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;


public class MainActivity extends AppCompatActivity {
    private Button enterJobBtn;
    private Button compareJobBtn;
    private long currentJobId;

    @Override
    protected void onStart() {
        super.onStart();

        App app = (App) getApplicationContext();
        enterJobBtn = (Button) findViewById(R.id.enterJobBtnID);
        if (getCurrentJobId(app) != -1) enterJobBtn.setText(R.string.edit_job);

        compareJobBtn = (Button) findViewById(R.id.compareOfferBtnID);
        compareJobBtn.setEnabled(isOfferMoreThanTwo(app));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private long getCurrentJobId(App app) {
        if (app.getCurrentJob() == null) currentJobId = -1;
        else currentJobId = app.getCurrentJob().jobOffer.id;
        return currentJobId;
    }

    private boolean isOfferMoreThanTwo(App app) {
        List<JobWithLocation> jobList = app.db.jobOfferDao().getAll();

        return jobList.size() > 1;
    }

    public void clickEnterJobBtn(View view){
        Intent intent = new Intent(this, JobDetails.class);
        intent.putExtra("JobId", currentJobId);
        startActivity(intent);
    }

    public void clickEnterOfferBtn(View view){
        Intent intent = new Intent(this, JobDetails.class);
        startActivity(intent);
    }

    public void clickAdjustBtn(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void clickCompareOfferBtn(View view){
        Intent intent = new Intent(this, JobList.class);
        startActivity(intent);
    }
}