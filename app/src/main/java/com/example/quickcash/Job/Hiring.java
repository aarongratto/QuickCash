package com.example.quickcash.Job;


/**
 * Author: Aaron Gratto
 * Date: 2020-11-04
 *
 * Hiring subclass that extends Job and represents the 'Hiring' job types
 */
public class Hiring extends Job {

    public Hiring(){}

    public Hiring(String jobTitle, String jobLocation, String jobDescription, double jobWage){
        super(jobTitle, jobLocation, jobDescription, jobWage);
    }

    @Override
    protected void setJobType() {
        this.jobType = "Hiring"; //set job type to "Hiring"
    }

}
