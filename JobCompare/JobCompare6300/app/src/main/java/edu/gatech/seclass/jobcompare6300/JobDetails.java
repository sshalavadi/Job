package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.ListIterator;

import edu.gatech.seclass.jobcompare6300.entities.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.entities.JobOffer;
import edu.gatech.seclass.jobcompare6300.entities.Location;
import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;

public class JobDetails extends AppCompatActivity {
    private EditText titleBox;
    private EditText companyBox;
    private EditText cityBox;
    private EditText stateBox;
    private EditText indexBox;
    private EditText yearlySalaryBox;
    private EditText yearlyBonusBox;
    private EditText retirementBox;
    private EditText relocationBox;
    private EditText trainingFundBox;

    private Button saveSettingBtn;
    private Button cancelSettingBtn;

    private boolean isJob;
    private long jobId;
    private App app;
    private JobWithLocation job;

    @Override
    protected void onStart() {
        super.onStart();

        Bundle b = getIntent().getExtras();
        if (b == null) isJob = false;
        else {
            isJob = true;
            jobId = b.getLong("JobId");
        }

        titleBox = (EditText) findViewById(R.id.titleInputID);
        companyBox  = (EditText) findViewById(R.id.companyInputID);
        cityBox  = (EditText) findViewById(R.id.cityInputID);
        stateBox  = (EditText) findViewById(R.id.stateInputID);
        indexBox  = (EditText) findViewById(R.id.indexInputID);
        yearlySalaryBox = (EditText) findViewById(R.id.yearlySalaryInputID);
        yearlyBonusBox  = (EditText) findViewById(R.id.yearlyBonusInputID);
        retirementBox  = (EditText) findViewById(R.id.retirementInputID);
        relocationBox  = (EditText) findViewById(R.id.relocationInputID);
        trainingFundBox  = (EditText) findViewById(R.id.trainingFundInputID);

        app = (App) getApplicationContext();
        if (isJob && jobId != -1) {
            job = app.getCurrentJob();
            titleBox.setText(job.jobOffer.getTitle() + "");
            companyBox.setText(job.jobOffer.getCompany() + "");
            cityBox.setText(job.getLocation().getCity() + "");
            stateBox.setText(job.getLocation().getState() + "");
            indexBox.setText(job.getLocation().getLivingCostIndex() + "");
            yearlySalaryBox.setText(job.jobOffer.getYearlySalary() + "");
            yearlyBonusBox.setText(job.jobOffer.getYearlyBonus() + "");
            retirementBox.setText(job.jobOffer.getRetirementBenefits() + "");
            relocationBox.setText(job.jobOffer.getRelocationStipend() + "");
            trainingFundBox.setText(job.jobOffer.getTrainingFund() + "");
        } else {
            job = new JobWithLocation();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
    }

    public void clickSaveBtn(View view){
        String title = titleBox.getText().toString();
        String company = companyBox.getText().toString();
        String city = cityBox.getText().toString();
        String state = stateBox.getText().toString();
        String index = indexBox.getText().toString();
        String yearlySalary = yearlySalaryBox.getText().toString();
        String yearlyBonus = yearlyBonusBox.getText().toString();
        String retirement = retirementBox.getText().toString();
        String relocation = relocationBox.getText().toString();
        String trainingFund = trainingFundBox.getText().toString();
        boolean failure = false;

        if (title.matches("")) {
            titleBox.setError("Please input");
            failure = true;
        }
        if (company.matches("")) {
            companyBox.setError("Please input");
            failure = true;
        }
        if (city.matches("")) {
            cityBox.setError("Please input");
            failure = true;
        }
        if (state.matches("")) {
            stateBox.setError("Please input");
            failure = true;
        }
        if (index.matches("")) {
            indexBox.setError("Please input");
            failure = true;
        }
        if (yearlySalary.matches("")) {
            yearlySalaryBox.setError("Please input");
            failure = true;
        }
        if (yearlyBonus.matches("")) {
            yearlyBonusBox.setError("Please input");
            failure = true;
        }
        if (retirement.matches("")) {
            retirementBox.setError("Please input");
            failure = true;
        }
        if (relocation.matches("")) {
            relocationBox.setError("Please input");
            failure = true;
        }
        if (trainingFund.matches("")) {
            trainingFundBox.setError("Please input");
            failure = true;
        }

        if (!failure) {
            try {
                if (isJob && jobId != -1) {
                    job.jobOffer.setTitle(title);
                    job.jobOffer.setCompany(company);

                    Location location = this.prepareLocation(job.getLocation(),city,state,Integer.parseInt(index));
                    job.setLocation(location);

                    job.jobOffer.setRelocationStipend(Double.parseDouble(relocation));
                    job.jobOffer.setTrainingFund(Double.parseDouble(trainingFund));
                    job.jobOffer.setYearlySalary(Double.parseDouble(yearlySalary));
                    job.jobOffer.setYearlyBonus(Double.parseDouble(yearlyBonus));
                    job.jobOffer.setRetirementBenefits(Double.parseDouble(retirement));
                    job.jobOffer.setRelocationStipend(Double.parseDouble(relocation));
                    job.jobOffer.setTrainingFund(Double.parseDouble(trainingFund));
                    job.calculateJobScore(app.db.comparisonSettingsDao().load());

                    app.db.jobOfferDao().update(job.jobOffer);
                }
                else {
                    Location location = this.prepareLocation(null,city,state,Integer.parseInt(index));

                    job = new JobWithLocation();
                    job.setLocation(location);
                    job.jobOffer.setTitle(title);
                    job.jobOffer.setCompany(company);
                    job.jobOffer.setRelocationStipend(Double.parseDouble(relocation));
                    job.jobOffer.setTrainingFund(Double.parseDouble(trainingFund));
                    job.jobOffer.setYearlySalary(Double.parseDouble(yearlySalary));
                    job.jobOffer.setYearlyBonus(Double.parseDouble(yearlyBonus));
                    job.jobOffer.setRetirementBenefits(Double.parseDouble(retirement));
                    job.jobOffer.setRelocationStipend(Double.parseDouble(relocation));
                    job.jobOffer.setTrainingFund(Double.parseDouble(trainingFund));
                    job.calculateJobScore(app.db.comparisonSettingsDao().load());
                    job.jobOffer.id = app.db.jobOfferDao().insert(job.jobOffer);

                }
                // Update currentJob
                app.setCurrentJob(job);
            } catch (InvalidParameterException ex) {
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }

            finish();
        }
    }

    public void clickCancelBtn(View view){
        finish();
    }

    private Location prepareLocation(Location location, String city, String state, int livingCostIndex) {
        if( location != null && location.id > 0 ) {
            if( location.getCity().equals(city) && location.getState().equals(state) ) {
                location.setLivingCostIndex(livingCostIndex);
                app.db.locationDao().update(location);
                return location;
            }
        }
        Location newLocation;
        List<Location> byCity = app.db.locationDao().findByCity(city, state);
        if( byCity.size() > 0 ) {
            newLocation = byCity.get(0);
            newLocation.setLivingCostIndex(livingCostIndex);
            app.db.locationDao().update(newLocation);
            return newLocation;
        }
        newLocation = new Location();
        newLocation.setLivingCostIndex(livingCostIndex);
        newLocation.setCity(city);
        newLocation.setState(state);
        newLocation.id = app.db.locationDao().insert(newLocation);
        return newLocation;
    }
}