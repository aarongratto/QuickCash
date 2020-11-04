package com.example.quickcash;

/**
 * Author: Aaron Gratto
 * Date: 2020-11-04
 *
 * Job class representing the jobs that can be added to the app
 */

abstract class Job {
    protected String jobType;
    protected String jobTitle;
    protected String jobLocation;
    protected double jobWage;

    public Job(){}

    protected void setJobTitle(String jobTitle){ this.jobTitle = jobTitle; }
    protected void setJobWage(double jobWage){ this.jobWage = jobWage; }


    public String getJobType() { return jobType; }
    public String getJobTitle() { return jobTitle; }
    public String getJobLocation() { return jobLocation; }
    public double getJobWage() { return jobWage; }


}

/**
 * Author: Aaron Gratto
 * Date: 2020-11-04
 *
 * Hiring class extends Job and represents the 'Hiring' job types
 */
class Hiring extends Job {

    public Hiring(String jobTitle, String jobLocation, double jobWage){
        this.jobType = "Hiring";
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobWage = jobWage;
    }

}

