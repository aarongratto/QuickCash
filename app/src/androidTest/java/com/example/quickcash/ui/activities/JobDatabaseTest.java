package com.example.quickcash.ui.activities;

import android.util.Log;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.quickcash.Job.Hiring;
import com.example.quickcash.Job.Job;
import com.example.quickcash.Job.LookingForWork;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JobDatabaseTest {
    //create two new 'Hiring' job objects
    Hiring h1 = new Hiring("Chef", "Truro", "We are looking to hire a new chef", 15.20);
    Hiring h2 = new Hiring("Software Engineer", "Halifax", "We are looking to hire a new Software Engineer", 20.56);
    LookingForWork l1 = new LookingForWork("Labor", "Antigonish", "I am looking for a labor job", 12.55);
    LookingForWork l2 = new LookingForWork("Volunteer", "Sydney", "I am looking for a volunteer position", 0.00);
    List<Job> jobsInDatabase = new ArrayList<>();


    @Rule
    public ActivityScenarioRule<MainPage> activityRule
            = new ActivityScenarioRule<>(MainPage.class);

    @Before
    public void setup() {

        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainPage>() {
            @Override
            public void perform(MainPage activity) {
                Log.d("TAG1", "firebase initialized");
                activity.addToDatabase(h1);
                activity.addToDatabase(h2);
                activity.addToDatabase(l1);
                activity.addToDatabase(l2);

                Log.d("TAG1", "before read");
                jobsInDatabase = activity.getJobsInDatabase();
                Log.d("TAG1", "after read");
            }
        });


    }

    public void teardown(){
        Log.d("TAG1", "start teardown");
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainPage>() {
            @Override
            public void perform(MainPage activity) {
                Log.d("TAG1", "wipe called");
                activity.wipeDatabase();
            }
        });
        Log.d("TAG1", "after teardown");
    }

    @Test
    public void jobDatabaseReadAndWrite(){
        List<String> jType = new ArrayList<>();
        List<String> jTitle = new ArrayList<>();
        List<String> jLocation = new ArrayList<>();
        List<Double> jWage = new ArrayList<>();

        Log.d("TAG1", "before loop");
        Log.d("TAG1", String.valueOf(jobsInDatabase.size()));
        for (int i = 0; i < jobsInDatabase.size(); i++){
            Log.d("TAG1", "new job -----------------------------------");

            jType.add(jobsInDatabase.get(i).getJobType());
            Log.d("TAG1", jobsInDatabase.get(i).getJobType());

            jTitle.add(jobsInDatabase.get(i).getJobTitle());
            Log.d("TAG1", jobsInDatabase.get(i).getJobTitle());

            jLocation.add(jobsInDatabase.get(i).getJobLocation());
            Log.d("TAG1", jobsInDatabase.get(i).getJobLocation());

            jWage.add(jobsInDatabase.get(i).getJobWage());
            Log.d("TAG1", String.valueOf(jobsInDatabase.get(i).getJobWage()));
        }
        Log.d("TAG1", "after loop");

        /*assertTrue(jType.contains("Hiring") && jType.contains("Looking for work"));
        assertTrue(jTitle.contains("Chef") && jTitle.contains("Software Engineer")
                && jTitle.contains("Labor") && jTitle.contains("Volunteer"));
        assertTrue(jLocation.contains("Truro") && jLocation.contains("Halifax")
                && jLocation.contains("Volunteer") && jLocation.contains("Sydney"));
        assertTrue(jWage.contains(15.20) && jWage.contains(20.56)
                && jWage.contains(12.55) && jWage.contains(0.00));*/
    }

}

