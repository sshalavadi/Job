package edu.gatech.seclass.jobcompare6300.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.InvalidParameterException;

@Entity(tableName = "comparison_settings")
public class ComparisonSettings {

    @PrimaryKey(autoGenerate = true)
    public long id;

    private int yearlySalaryWeight = 1;
    private int yearlyBonusWeight = 1;
    private int retirementBenefitsWeight = 1;
    private int relocationStipendWeight = 1;
    private int trainingFundWeight = 1;

    private void checkParam(int parameter) {
        if( parameter < 1 ) {
            throw new InvalidParameterException("weight need to be >= 1");
        }
    }

    public double getWeightSum() {
        return (double) yearlySalaryWeight + yearlyBonusWeight + retirementBenefitsWeight
                + relocationStipendWeight + trainingFundWeight;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        checkParam(yearlySalaryWeight);
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        checkParam(yearlyBonusWeight);
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getRetirementBenefitsWeight() {
        return retirementBenefitsWeight;
    }

    public void setRetirementBenefitsWeight(int retirementBenefitsWeight) {
        checkParam(retirementBenefitsWeight);
        this.retirementBenefitsWeight = retirementBenefitsWeight;
    }

    public int getRelocationStipendWeight() {
        return relocationStipendWeight;
    }

    public void setRelocationStipendWeight(int relocationStipendWeight) {
        checkParam(relocationStipendWeight);
        this.relocationStipendWeight = relocationStipendWeight;
    }

    public int getTrainingFundWeight() {
        return trainingFundWeight;
    }

    public void setTrainingFundWeight(int trainingFundWeight) {
        checkParam(trainingFundWeight);
        this.trainingFundWeight = trainingFundWeight;
    }
}
