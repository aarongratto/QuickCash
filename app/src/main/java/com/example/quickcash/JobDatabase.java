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
        Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
        while (it.hasNext()){
            DataSnapshot jobSnapshot = it.next();
            String currentJob = jobSnapshot.getValue().toString();
            Log.d("TAG1", "new read: " +currentJob);
            if (currentJob.endsWith("jobType=Hiring}")){
                Hiring hiringJob = jobSnapshot.getValue(Hiring.class);
                jobsInDatabase.add(hiringJob);
            }
            else {
                LookingForWork lookingJob = jobSnapshot.getValue(LookingForWork.class);
                jobsInDatabase.add(lookingJob);
            }
        }
        Log.d("TAG1", "Database size: " +jobsInDatabase.size());
        return jobsInDatabase;
    }

}
