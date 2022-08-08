package edu.gatech.seclass.jobcompare6300;

import static org.junit.Assert.*;

import org.junit.Test;

import java.security.InvalidParameterException;

import edu.gatech.seclass.jobcompare6300.entities.*;
import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;

public class EntitiesUnitTest {

    @Test
    public void yearlySalaryAdjustedForCostLiving_isCorrect() {
        double salary = 1234.57;
        Location location = new Location();
        location.setLivingCostIndex(100);

        JobWithLocation job = new JobWithLocation();
        job.setLocation( location );
        job.jobOffer.setYearlySalary(salary);

        assertEquals(salary, job.getYearlySalaryAdjustedForCostLiving(), 0.001);

        location.setLivingCostIndex(50);
        assertEquals(salary*2, job.getYearlySalaryAdjustedForCostLiving(), 0.001);
    }

    @Test
    public void yearlyBonusAdjustedForCostLiving_isCorrect() {
        double bonus = 1234.57;
        Location location = new Location();
        location.setLivingCostIndex(100);

        JobWithLocation job = new JobWithLocation();
        job.setLocation( location );
        job.jobOffer.setYearlyBonus(bonus);

        assertEquals(bonus, job.getYearlyBonusAdjustedForCostLiving(), 0.001);

        location.setLivingCostIndex(50);
        assertEquals(bonus*2, job.getYearlyBonusAdjustedForCostLiving(), 0.001);
    }

    @Test
    public void jobScore_isCorrect() {
        double salary = 1234.57;
        double bonus = 890.12;
        double retirement = 50.0;
        double relocation = 56.34;
        double training = 345.76;

        Location location = new Location();
        location.setLivingCostIndex(100);

        JobWithLocation job = new JobWithLocation();
        job.setLocation( location );
        job.jobOffer.setYearlySalary(salary);
        job.jobOffer.setYearlyBonus(bonus);
        job.jobOffer.setRetirementBenefits(retirement);
        job.jobOffer.setRelocationStipend(relocation);
        job.jobOffer.setTrainingFund(training);

        ComparisonSettings settings = new ComparisonSettings();

        job.calculateJobScore(settings);
        assertEquals(
                (salary + bonus + (retirement*salary/100) + relocation + training) / 5,
                job.jobOffer.getJobScore(), 0.01
        );

        settings.setYearlySalaryWeight(2);
        settings.setRelocationStipendWeight(2);
        job.calculateJobScore(settings);
        assertEquals(
                (2*salary + bonus + (retirement*salary/100) + 2*relocation + training) / 7,
                job.jobOffer.getJobScore(), 0.01
        );
    }

    @Test(expected = InvalidParameterException.class)
    public void location_invalidParam1() {
        Location location = new Location();
        location.setLivingCostIndex(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void comparisonSettings_invalidParam1() {
        ComparisonSettings settings = new ComparisonSettings();

        settings.setYearlySalaryWeight(0);
    }
    @Test(expected = InvalidParameterException.class)
    public void comparisonSettings_invalidParam2() {
        ComparisonSettings settings = new ComparisonSettings();

        settings.setYearlyBonusWeight(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void comparisonSettings_invalidParam3() {
        ComparisonSettings settings = new ComparisonSettings();

        settings.setRetirementBenefitsWeight(0);
    }

    @Test(expected = InvalidParameterException.class)
    public void comparisonSettings_invalidParam4() {
        ComparisonSettings settings = new ComparisonSettings();

        settings.setRelocationStipendWeight(0);
    }

    @Test(expected = InvalidParameterException.class)
    public void comparisonSettings_invalidParam5() {
        ComparisonSettings settings = new ComparisonSettings();

        settings.setTrainingFundWeight(0);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam1() {
        JobOffer job = new JobOffer();

        job.setYearlySalary(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam2() {
        JobOffer job = new JobOffer();

        job.setYearlyBonus(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam3() {
        JobOffer job = new JobOffer();

        job.setRetirementBenefits(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam4() {
        JobOffer job = new JobOffer();

        job.setRelocationStipend(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam5() {
        JobOffer job = new JobOffer();

        job.setTrainingFund(-1);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam6() {
        JobOffer job = new JobOffer();

        job.setTrainingFund(18000.01);
    }

    @Test(expected = InvalidParameterException.class)
    public void jobOffer_invalidParam7() {
        JobOffer job = new JobOffer();

        job.setRetirementBenefits(100.01);
    }
}

