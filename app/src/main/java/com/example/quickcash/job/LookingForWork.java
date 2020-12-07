package com.example.quickcash.job;

/**
 * Author: Aaron Gratto
 * Date: 2020-11-04
 *
 * LookingForWork subclass that extends Job and represents the 'Looking for work' job types
 */
public class LookingForWork extends Job {

    public LookingForWork(){}

    public LookingForWork(String jobTitle, String jobLocation, String jobDescription, double jobWage){
        super(jobTitle, jobLocation, jobDescription, jobWage);
    }

    @Override
    protected void setJobType() {
        this.jobType = "Looking for work"; //set job type to "Looking for work"
    }
}
