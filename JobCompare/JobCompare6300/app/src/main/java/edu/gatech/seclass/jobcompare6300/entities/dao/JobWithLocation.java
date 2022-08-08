package edu.gatech.seclass.jobcompare6300.entities.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.gatech.seclass.jobcompare6300.entities.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.entities.JobOffer;
import edu.gatech.seclass.jobcompare6300.entities.Location;

public class JobWithLocation {
    @Embedded
    public JobOffer jobOffer;

    @Relation(
            parentColumn = "locationId",
            entityColumn = "id"
    )
    private Location location;

    public void setLocation(Location location) {
        this.location = location;
        this.jobOffer.setLocationId(location.id);
    }

    public Location getLocation() {
        return location;
    }

    public JobWithLocation() {
        this.jobOffer = new JobOffer();
        this.location = new Location();
    }

    public void calculateJobScore(ComparisonSettings comparisonSettings) {

        double AYSweight = comparisonSettings.getYearlySalaryWeight() / comparisonSettings.getWeightSum();
        double AYBweight = comparisonSettings.getYearlyBonusWeight() / comparisonSettings.getWeightSum();
        double RBPweight = comparisonSettings.getRetirementBenefitsWeight() / comparisonSettings.getWeightSum();
        double RSweight = comparisonSettings.getRelocationStipendWeight() / comparisonSettings.getWeightSum();
        double TDFweight = comparisonSettings.getTrainingFundWeight() / comparisonSettings.getWeightSum();

        jobOffer.jobScore =
                this.getYearlySalaryAdjustedForCostLiving() * AYSweight +
                        this.getYearlyBonusAdjustedForCostLiving() * AYBweight +
                        (jobOffer.getRetirementBenefits() * this.getYearlySalaryAdjustedForCostLiving() / 100) * RBPweight +
                        jobOffer.getRelocationStipend() * RSweight +
                        jobOffer.getTrainingFund() * TDFweight
        ;
    }

    public double getYearlySalaryAdjustedForCostLiving() {
        return jobOffer.getYearlySalary() * 100 / this.location.getLivingCostIndex();
    }

    public double getYearlyBonusAdjustedForCostLiving() {
        return jobOffer.getYearlyBonus() * 100 / this.location.getLivingCostIndex();
    }
}
