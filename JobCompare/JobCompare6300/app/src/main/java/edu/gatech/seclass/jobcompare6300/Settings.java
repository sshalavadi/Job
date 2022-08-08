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
import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;

public class Settings extends AppCompatActivity {
    private EditText yearlySalaryBox;
    private EditText yearlyBonusBox;
    private EditText retirementBox;
    private EditText relocationBox;
    private EditText trainingFundBox;

    private Button saveSettingBtn;
    private Button cancelSettingBtn;

    private static ComparisonSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        yearlySalaryBox = (EditText) findViewById(R.id.yearlySalaryInputID);
        yearlyBonusBox  = (EditText) findViewById(R.id.yearlyBonusInputID);
        retirementBox  = (EditText) findViewById(R.id.retirementInputID);
        relocationBox  = (EditText) findViewById(R.id.relocationInputID);
        trainingFundBox  = (EditText) findViewById(R.id.trainingFunInputID);

        App app = (App) getApplicationContext();
        settings = app.db.comparisonSettingsDao().load();

        yearlySalaryBox.setText(settings.getYearlySalaryWeight()+"");
        yearlyBonusBox.setText(settings.getYearlyBonusWeight()+"");
        retirementBox.setText(settings.getRetirementBenefitsWeight()+"");
        relocationBox.setText(settings.getRelocationStipendWeight()+"");
        trainingFundBox.setText(settings.getTrainingFundWeight()+"");
    }

    public void clickSaveBtn(View view){
        String yearlySalary = yearlySalaryBox.getText().toString();
        String yearlyBonus = yearlyBonusBox.getText().toString();
        String retirement = retirementBox.getText().toString();
        String relocation = relocationBox.getText().toString();
        String trainingFund = trainingFundBox.getText().toString();
        boolean failure = false;

        if (yearlySalary.matches("")) {
            yearlySalaryBox.setError("Please set weight");
            failure = true;
        }
        if (yearlyBonus.matches("")) {
            yearlyBonusBox.setError("Please set weight");
            failure = true;
        }
        if (retirement.matches("")) {
            retirementBox.setError("Please set weight");
            failure = true;
        }
        if (relocation.matches("")) {
            relocationBox.setError("Please set weight");
            failure = true;
        }
        if (trainingFund.matches("")) {
            trainingFundBox.setError("Please set weight");
            failure = true;
        }

        if (!failure) {
            try {
                settings.setYearlySalaryWeight(Integer.parseInt(yearlySalary));
                settings.setYearlyBonusWeight(Integer.parseInt(yearlyBonus));
                settings.setRetirementBenefitsWeight(Integer.parseInt(retirement));
                settings.setRelocationStipendWeight(Integer.parseInt(relocation));
                settings.setTrainingFundWeight(Integer.parseInt(trainingFund));
            } catch (InvalidParameterException ex) {
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }



            // update database
            App app = (App) getApplicationContext();

            Toast message = Toast.makeText(app,"Updating job scores...",Toast.LENGTH_LONG);
            message.show();

            app.db.comparisonSettingsDao().update(settings);

            // update score of job offers
            List<JobWithLocation> jobList = app.db.jobOfferDao().getAll();
            ListIterator<JobWithLocation> listIterator = jobList.listIterator();
            while(listIterator.hasNext()) {
                listIterator.next().calculateJobScore(settings);
            }

            message.cancel();

            finish();
        }
    }

    public void clickCancelBtn(View view){
        finish();
    }
}