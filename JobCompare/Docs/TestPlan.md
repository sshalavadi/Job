# Test Plan

## 1 Testing Strategy

### 1.1 Overall strategy
| ** test ** | **activities**
|:----:| :----:| :----:|
| Unit testing | create test case/implement automation code 
| Unit testing | Report test result 
| Integration testing & system testing | create test case/implement manual testing 
| Integration testing & system testing | Report test result 
| Regression testing | create test case/implement manual testing 
| Regression testing | Report test result

### 1.2 Test Selection
| ** test ** | **technique** | ** clarify ** |
|:----:| :----:| :----:|
| Unit testing | whitebox testing | easier to cover all statements |
| Integration testing | blackbox testing | the app includes ui so it's easier to check manually |
| System testing | blackbox testing | the app includes ui so it's easier to check manually |
| Regression testing | blackbox testing | the app includes ui so it's easier to check manually |

### 1.3 Adequacy Criterion
| ** test ** | **technique** | ** clarify ** |
|:----:| :----:| :----:|
| Unit testing | 100% statement coverage | in whitebox testing it's easy to check |
| Integration testing | 100% condition coverage | to make sure all conditions were tested |
| System testing | 100% condition coverage | it's same with integration testing as this is a simple app |
| Regression testing | 40% condition coverage | in regression testing we focus on changed part and critical path only |


### 1.4 Bug Tracking
For bugs identified, we will use Github issues and communicate in Slack. For feature requests, we are considering using the Github projects section.

### 1.5 Technology
The technology used for unit testing was the JUnit testing suite.

## 2 Test Cases
### 2.1 Unit test 
|                    **Test Name**                   |     **Type**     |                               **Purpose**                               |                                         **Steps**                                         |           **Expected**          |            **Actual**            | **Pass/Fail** |
|:--------------------------------------------------:|:----------------:|:-----------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------:|:-------------------------------:|:--------------------------------:|:-------------:|
|     yearlySalaryAdjustedForCostLiving_isCorrect    | EntitiesUnitTest |              Validates salary adjusted for COL is correct.              | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" |         1234.57/2469.14         |          1234.57/2469.14         |      Pass     |
|     yearlyBonusAdjustedForCostLiving_isCorrect     | EntitiesUnitTest |               Validates bonus adjusted for COL is correct.              | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" |         1234.57/2469.14         |          1234.57/2469.14         |      Pass     |
|                 jobScore_isCorrect                 | EntitiesUnitTest |                      Validates jobScore is correct.                     | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" |    628.815/633.5692857142858    |     628.815/633.5692857142858    |      Pass     |
|               location_invalidParam1               | EntitiesUnitTest |                  Validation on invalid location params.                 | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|          comparisonSettings_invalidParam1          | EntitiesUnitTest |                Validation on invalid comparison settings.               | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|          comparisonSettings_invalidParam2          | EntitiesUnitTest |                Validation on invalid comparison settings.               | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|          comparisonSettings_invalidParam3          | EntitiesUnitTest |                Validation on invalid comparison settings.               | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|          comparisonSettings_invalidParam4          | EntitiesUnitTest |                Validation on invalid comparison settings.               | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|          comparisonSettings_invalidParam5          | EntitiesUnitTest |                Validation on invalid comparison settings.               | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|               jobOffer_invalidParam1               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|               jobOffer_invalidParam2               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|               jobOffer_invalidParam3               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class | InvalidParameterException. class |      Pass     |
|               jobOffer_invalidParam4               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|               jobOffer_invalidParam5               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|               jobOffer_invalidParam6               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|               jobOffer_invalidParam7               | EntitiesUnitTest |                   Validation on invalid jobOffer data.                  | cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run EntitiesUnitTest" | InvalidParameterException.class |  InvalidParameterException.class |      Pass     |
|   writeLocation.assertNotEquals(0, location.id);   |   DatabaseTest   |                  Ensures inserted location ID is not 0.                 |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |                1                |                 1                |      Pass     |
|                writeLocation.getCity               |   DatabaseTest   |                          Confirms city matches.                         |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |          San Francisco          |           San Francisco          |      Pass     |
|               writeLocation.getState               |   DatabaseTest   |                         Confirms state matches.                         |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |                CA               |                CA                |      Pass     |
|          writeLocation.getLivingCostIndex          |   DatabaseTest   |                   Confirms living cost index matches.                   |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |               200               |                200               |      Pass     |
|           writeLocation.byCity.get(0).id           |   DatabaseTest   |                     Confirms search by city matches.                    |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |                !0               |                !0                |      Pass     |
|        writeComparisonSettings.nullAssertion       |   DatabaseTest   |           Confirms comparison setting is null before updating.          |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |               null              |               null               |      Pass     |
|          writeComparisonSettings.nonZeroID         |   DatabaseTest   |     Confirms comparison settings has a non-zero ID after insertion.     |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |                1                |                 1                |      Pass     |
|    writeComparisonSettings.getYearlySalaryWeight   |   DatabaseTest   |        Confirms getYearlySalaryWeight returns the expected value.       |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |             variable            |             variable             |      Pass     |
| writeComparisonSettings.getRelocationStipendWeight |   DatabaseTest   |     Confirms getRelocationStipendWeight returns the expected value.     |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |             variable            |             variable             |      Pass     |
|            writeJobOffer.checkNonZeroID            |   DatabaseTest   |          Confirms job offer has a non-zero ID after insertion.          |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |                !0               |                !0                |      Pass     |
|         writeJobOffer.checkNonZeroOfferList        |   DatabaseTest   |   Confirms the offer list is not empty after insertion of a job offer.  |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |                !0               |                !0                |      Pass     |
|       writeJobOffer.firstElementLocationCity       |   DatabaseTest   |      Confirms the inserted element is in the beginning of the list.     |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |               n/a               |                n/a               |      Pass     |
|       writeJobOffer.firstElementLocationState      |   DatabaseTest   |      Confirms the inserted element is in the beginning of the list.     |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |             index 0             |              index 0             |      Pass     |
|  writeJobOffer.firstElementLocationLivingCostIndex |   DatabaseTest   | Confirms the cost of living matches between returned value and inserted |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   |               200               |                200               |      Pass     |
| deleteJobOffer                                     | DatabaseTest     | Ensures that the inserted element is also deleted.                      |   cd app/java/jobcompare630, right click EntitiesUnitTest and select "Run DatabaseTest"   | 0                               | 0                                |     Pass      |

### 2.2 Integration test / system test
|   **Test Name** |  **Purpose**  |    **Steps**   |           **Expected**          |            **Actual**            | **Pass/Fail** |
|:------------------------:|:----------------:|:-----------------------------------------------------------------------------------------:|:-------------------------------:|:--------------------------------:|:-------------:|
| EnterCurrentJob_isCorrect| Validate user can enter current job when no current job exist.| 1, open app 2, click "Enter current job" button 3. input all fields with reasonable value 4. click "Save" button | System navigate back to main menu. The "Enter current job" button changed to "Edit current job" button. Click "Edit current job" button, the input values will appear. |  As expected |      Pass     |
| EditCurrentJob_isCorrect| Validate user can edit current job when current job exists.| 1, open app 2, click "Edit current job" button 3. input all fields with reasonable value 4. click "Save" button | System navigate back to main menu. Click "Edit current job" button, the input values will appear. |  As expected |      Pass     |
| EnterNewOffer_isCorrect| Validate user can enter new offer.| 1, open app 2, click "Enter new offer" button 3. input all fields with reasonable value 4. click "Save" button | System navigate back to main menu. If there are at least 2 offers, click "Compare" button, the new offer will appear in the list. Select the new offer and other offer, click "Compare" button, the input values will display correctly.|  As expected |      Pass     |
| AdjustSettings_isCorrect| Validate user can adjust compare settings.| 1, open app 2, click "Adjust settings" button 3. input all fields with integer value 4. click "Save" button | The default value of settings are all 1. After click "Save" button, system navigate back to main menu. Click "Adjust settings" button, the input values will appear. |  As expected |      Pass     |
| EnterCurrentJob_Cancel| Validate user can cancel on job detail page.| 1, open app 2, click "Enter current job" button 3. input all fields with reasonable value 4. click "Cancel" button |  System navigate back to main menu. Nothing changed. |  As expected |      Pass     |
| EnterNewOffer_Cancel| Validate user can enter new offer.| 1, open app 2, click "Enter new offer" button 3. input all fields with reasonable value 4. click "Cancel" button | System navigate back to main menu. Nothing changed. |  As expected |      Pass     |
| AdjustSettings_Cancel| Validate user can adjust compare settings.| 1, open app 2, click "Adjust settings" button 3. input all fields with integer value 4. click "Cancel" button | System navigate back to main menu. Nothing changed. |  As expected |      Pass     |
| CompareJobOffer_isCorrect| Validate user can compare job offers when at least 2 job/offer exist.| 1, open app 2, click "Compare job offer" button 3. select 2 job offers 4. click "Compare" button | Step3, all job/offer will be listed with title/company and sorted by score. Step4, compare page will list the job details of selected job offers in a table. |  As expected |      Pass     |
| DeleteJobOffer_isCorrect| Validate user can delete job offers when at least 2 job/offer exist.| 1, open app 2, click "Compare job offer" button 3. select any job offers 4. click "Delete" button 5. click "Confirm" button | Step4, confirmation page will pop up with "Confirm" and "Cancel" button. Step 5, system will navigate back to job list page, the deleted offers will disappear. If there is no offer, system will navigate back to main menu and "Compare job offer" button will disable. |  As expected |      Pass     |
| CompareJobOffer_Back| Validate user can go back to main menu from job list page.| 1, open app 2, click "Compare job offer" button 3. click "Back" arrow | System navigate back to main menu. Nothing changed. |  As expected |      Pass     |
| DeleteJobOffer_Cancel| Validate user can delete job offers when at least 2 job/offer exist.| 1, open app 2, click "Compare job offer" button 3. select any job offers 4. click "Delete" button 5. click "Cancel" button | The pop up window disappear. Nothing changed. |  As expected |      Pass     |
| SetCurrent_isCorrect| Validate user can set job offer to current job.| 1, open app 2, click "Compare job offer" button 3. select any job offer 4. click "Set current" button | The current job will change to the selected offer with background yellow. |  As expected |      Pass     |

### 2.3 Regression test 
|   **Test Name** |  **Purpose**  |    **Steps**   |           **Expected**          |            **Actual**            | **Pass/Fail** |
|:------------------------:|:----------------:|:-----------------------------------------------------------------------------------------:|:-------------------------------:|:--------------------------------:|:-------------:|
| EnterCurrentJob_isCorrect| Validate user can enter current job when no current job exist.| 1, open app 2, click "Enter current job" button 3. input all fields with reasonable value 4. click "Save" button | System navigate back to main menu. The "Enter current job" button changed to "Edit current job" button. Click "Edit current job" button, the input values will appear. |  As expected |      Pass     |
| EnterNewOffer_isCorrect| Validate user can enter new offer.| 1, open app 2, click "Enter new offer" button 3. input all fields with reasonable value 4. click "Save" button | System navigate back to main menu. If there are at least 2 offers, click "Compare" button, the new offer will appear in the list. Select the new offer and other offer, click "Compare" button, the input values will display correctly.|  As expected |      Pass     |
| AdjustSettings_isCorrect| Validate user can adjust compare settings.| 1, open app 2, click "Adjust settings" button 3. input all fields with integer value 4. click "Save" button | The default value of settings are all 1. After click "Save" button, system navigate back to main menu. Click "Adjust settings" button, the input values will appear. |  As expected |      Pass     |
| CompareJobOffer_isCorrect| Validate user can compare job offers when at least 2 job/offer exist.| 1, open app 2, click "Compare job offer" button 3. select 2 job offers 4. click "Compare" button | Step3, all job/offer will be listed with title/company and sorted by score. Step4, compare page will list the job details of selected job offers in a table. |  As expected |      Pass     |
| SetCurrent_isCorrect| Validate user can set job offer to current job.| 1, open app 2, click "Compare job offer" button 3. select any job offer 4. click "Set current" button | The current job will change to the selected offer with background yellow. |  As expected |      Pass     |
