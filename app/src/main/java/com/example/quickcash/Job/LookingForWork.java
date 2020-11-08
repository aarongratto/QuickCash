package com.example.quickcash.Job;

/**
 * Author: Aaron Gratto
 * Date: 2020-11-04
 *
 * LookingForWork subclass that extends Job and represents the 'Looking for work' job types
 */
public class LookingForWork extends Job {

    public LookingForWork(){}

    public LookingForWork(String jobTitle, String jobLocation, String jobDescription, double jobWage){
        this.jobType = "Looking for work"; //set job type to "Looking for work"
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobWage = jobWage;
    }

}
