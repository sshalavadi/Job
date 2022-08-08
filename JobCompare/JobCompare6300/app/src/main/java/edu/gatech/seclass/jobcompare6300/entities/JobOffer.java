package edu.gatech.seclass.jobcompare6300.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.security.InvalidParameterException;

@Entity(tableName = "job_offers", indices = {
        @Index(value = {"jobScore"})
})
public class JobOffer {

    @PrimaryKey(autoGenerate = true)
    public long id;

    private String title;
    private String company;
    private long locationId;
    private double yearlySalary;
    private double yearlyBonus;
    private double retirementBenefits;
    private double relocationStipend;
    private double trainingFund;
    public double jobScore;

    private void checkParam(double parameter, String paramName) {
        if( parameter < 0 ) {
            throw new InvalidParameterException(paramName + " need to be >= 0");
        }
    }




    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        checkParam(yearlySalary,"yearlySalary");
        this.yearlySalary = yearlySalary;
    }

    public double getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(double yearlyBonus) {
        checkParam(yearlyBonus, "yearlyBonus");
        this.yearlyBonus = yearlyBonus;
    }

    public double getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(double retirementBenefits) {
        checkParam(retirementBenefits, "retirementBenefits");
        if( retirementBenefits > 100.0 ) {
            throw new InvalidParameterException("retirementBenefits need to be <= 100");
        }
        this.retirementBenefits = retirementBenefits;
    }

    public double getRelocationStipend() {
        return relocationStipend;
    }

    public void setRelocationStipend(double relocationStipend) {
        checkParam(relocationStipend,"relocationStipend");
        this.relocationStipend = relocationStipend;
    }

    public double getTrainingFund() {
        return trainingFund;
    }

    public void setTrainingFund(double trainingFund) {
        checkParam(trainingFund,"trainingFund");
        if( trainingFund > 18000.0 ) {
            throw new InvalidParameterException("trainingFund need to be <= 18,000");
        }
        this.trainingFund = trainingFund;
    }

    public double getJobScore() { return jobScore; }

}
