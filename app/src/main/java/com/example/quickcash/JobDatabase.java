package com.example.quickcash;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quickcash.Job.Hiring;
import com.example.quickcash.Job.Job;
import com.example.quickcash.Job.LookingForWork;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Aaron Gratto
 * Date: 2020-12-02
 *
 * Class for the Job Database and its functions/methods
 */

public class JobDatabase {
    DatabaseReference db;
    List<Job> jobsInDatabase = new ArrayList<>();

    public JobDatabase(){
        db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobsInDatabase = synchronizeDatabase(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public DatabaseReference getJobDBReference(){
        return db;
    }

    public void addToDatabase(Job job) {
        db.child(String.valueOf(System.currentTimeMillis())).setValue(job);
        Log.d("TAG1", "new job added: " +job.getJobTitle());
    }

    public List<Job> getJobsInDatabase(){
        return jobsInDatabase;
    }

    public void wipeDatabase(){
        db.removeValue();
        Log.d("TAG1", "data wiped");
    }

    public List<Job> synchronizeDatabase(DataSnapshot snapshot){
        List<Job> tempDatabaseList = new ArrayList<>();
        Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
        while (it.hasNext()){
            DataSnapshot jobSnapshot = it.next();
            String currentJob = jobSnapshot.getValue().toString();
            Log.d("TAG1", "new read: " +currentJob);
            if (currentJob.endsWith("jobType=Hiring}")){
                Hiring hiringJob = jobSnapshot.getValue(Hiring.class);
                tempDatabaseList.add(hiringJob);
            }
            else {
                LookingForWork lookingJob = jobSnapshot.getValue(LookingForWork.class);
                tempDatabaseList.add(lookingJob);
            }
        }
        Log.d("TAG1", "Database size: " +tempDatabaseList.size());
        return tempDatabaseList;
    }

    public List<Job> getJobMatches(String titlePreference, String locationPreference){
        List<Job> jobMatches = new ArrayList<>();

        //loop through jobs retrieved from database
        Log.d("TAG1", "size: " +jobsInDatabase.size());
        for (int i = 0; i < jobsInDatabase.size(); i++){
            Job currentJob = jobsInDatabase.get(i);
            String currentJobTitle = currentJob.getJobTitle();
            String currentJobLocation = currentJob.getJobLocation();

            //if there's a matching in the title and location, add the job to the jobMatches ArrayList
            if (currentJobTitle.contains(titlePreference) || currentJobLocation.equals(locationPreference)){
                Log.d("TAG1", "match: " + currentJob.getJobLocation());
                jobMatches.add(currentJob);
            }
        }

        //return the ArrayList of jobs that match the user's search preferences
        return jobMatches;
    }
}
