package com.example.quickcash.job;

/**
 * Author: Aaron Gratto
 * Date: 2020-11-04
 *
 * Job class representing the jobs that can be added to the app
 */

/*
 * abstract job class (should not be used in practice, instead use the 'Hiring' and
 * 'LookingForWork' subclasses )
 */
public abstract class Job{
    //jobs have types, titles, locations, and wages
    protected String jobType;
    protected String jobTitle;
    protected String jobLocation;
    protected String jobDescription;
    protected String jobPreference;
    protected double jobWage;
    protected int jobID;
    protected String jobCreator;

    public Job(){}

    /*
     * No need for another Job constructor as 'Hiring' and 'Looking for Work' objects
     * will be used instead
     */
    public Job(String jobTitle, String jobLocation, String jobDescription, double jobWage){
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobWage = jobWage;
        setJobType();
    }

    // Force classes to set a job type
    protected abstract void setJobType();

    protected void setJobTitle(String jobTitle){ this.jobTitle = jobTitle; }
    protected void setJobWage(double jobWage){ this.jobWage = jobWage; }

    public String getJobType() { return jobType; }
    public String getJobTitle() { return jobTitle; }
    public String getJobLocation() { return jobLocation; }
    public String getJobDescription() { return jobDescription; }
    public double getJobWage() { return jobWage; }
    public String getPreference() { return jobPreference; }
    public void setPreference(String preference) {
        jobPreference = preference;
    }
    public int getJobID(){return jobID;}
    public void setJobID(int jobID){this.jobID = jobID;}
    public String getJobCreator(){return jobCreator;}
    public void setCreator(String jobCreator){this.jobCreator = jobCreator;}
}

