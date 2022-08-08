# Requirements

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet). 
    
    * I addressed these requirements in the Use Case Diagram (page 1) and in more details in the State Diagram (page 4).  
  
  
2. When choosing to enter current job details, a user will:

    a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
        
           i. Title
          ii. Company
         iii. Location (entered as city and state)
          iv. Cost of living in the location (expressed as an index)
           v. Yearly salary
          vi. Yearly bonus
         vii. Retirement benefits (as percentage matched) (given as an integer from 0 to 100 inclusive)
        viii. Relocation stipend
          ix. Training and development fund ($0 to $18000 inclusive annually)

    * The attributes of the job offer are represented in the Class Diagram (page 2), specifically classes `JobOffer` and `Location`. We decided to create the class `Location` to hold the living cost index and to avoid incosistency with differentes indexes for the same location (city/state). 
  
  
    b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
        
    * This requirement is shown in the State Diagram (page 4).
  
    
3. When choosing to enter job offers, a user will:
    
    a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
        
    * Use Cases 5 and 6 represent the reutilization of this interface to enter the job offer details.

    b. Be able to either save the job offer details or cancel.
 
    * This requirement is shown in the State Diagram (page 4).

    c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
     
    * This requirement is shown in the State Diagram (page 4).
    
4. When adjusting the comparison settings, the user can assign integer weights to:
    
    a. Yearly salary

    b. Yearly bonus
    
    c. Retirement benefits
    
    d. Relocation stipend

    e. Training and development fund 

    If no weights are assigned, all factors are considered equal.

    * These attrbitutes are represented in the Class Diagram (page 2), class `ComparisonSettings`. The State Diagram (page 4) has a note to address the behavior when no weights are assigned.

5. When choosing to compare job offers, a user will:

    a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
 
    * This requirement is shown in the State Diagram (page 4).
    
    b. Select two jobs to compare and trigger the comparison.
 
    * This requirement is shown in the State Diagram (page 4). The user selects the first job in the list, then select a second job offer to do the comparison.
    
    c. Be shown a table comparing the two jobs, displaying, for each job:

          i. Title
          ii. Company
         iii. Location
          iv. Yearly salary adjusted for cost of living
           v. Yearly bonus adjusted for cost of living
          vi. Retirement benefits 
         vii. Relocation stipend
        viii. Training and development fund
     
    * This requirement is shown in the State Diagram (page 4). The computed fields are shown in the Class Diagram (page 2).
    
    d. Be offered to perform another comparison or go back to the main menu.
     
    * This requirement is shown in the State Diagram (page 4).
    
6. When ranking jobs, a job’s score is computed as the weighted sum of:

    AYS + AYB + (RBP * AYS / 100) + RS + TDF

    where:
    AYS = yearly salary adjusted for cost of living
    AYB = yearly bonus adjusted for cost of living
    RBP = retirement benefits percentage
    RS = relocation stipend
    TDF = training and development fund

    For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:


    2/7 * AYS + 1/7 * AYB + 1/7 * (RBP * AYS / 100) + 2/7 * RS + 1/7 * TDF
     
    * This requirements are detailed in page 5 as a pseudo code.

7. The user interface must be intuitive and responsive.

    * This requirement is shown as a constraint in the Deployment Diagram (page 3).

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

    * This requirement is shown as a constraint in the Deployment Diagram (page 3).

Use Case Model 1: 
Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

1. User opens the app
2. User can access Main Menu, Current Job, Job Catalog)
3. User enters Current Job or has option to save Current Job
4. User enters Current Job and is able to enter the job offer details
5. User can save Job along job title and company name.
6. Return to Main Menu
7. User selects Compare and if shown the ranking of all the job offers using the weighted sum formula.
8. All jobs are ranked by their respective weighted sum and ranked from best to worst.
9. Each job in the compare displays the Job Title and Job Position.
10. User can return to the Menu and exit 

User Case Model 2: (Refer to User Diagram 1)
1. User opens the app
2. User can access Main Menu
3. User proceeds to 
    a. User enters a Job Offer and save with a specified name
    b. User enters Job details and saves the Job Offer to the Job Catalog (Job Catalog holds a list of all the available jobs that the user has added) --> User returns to Job Menu
4. User enter to Job Catalog 
5. User selects to comparison settings and then selects desired weights)
6. User will return to Job Catalog with weight settings saved into app
7. User will be list of Jobs (By Title and Company)
8. User may select any two jobs to compare (Current Job will also be listed)
9. User returns to Main Menu
