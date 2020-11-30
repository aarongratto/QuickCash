package com.example.quickcash.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.Job.Hiring;
import com.example.quickcash.Job.Job;
import com.example.quickcash.Job.LookingForWork;
import com.example.quickcash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainPage extends AppCompatActivity {
    DatabaseReference db;
    int key = 0;
    final List<Job> jobsInDatabase = new ArrayList<>();
    private Button createJobButton;
    private Button searchJobButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getUIElements();

        initializeFirebase();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                synchronizeDatabase(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getUIElements() {
        createJobButton = (Button) findViewById(R.id.jobCreateJobButton);
        createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCJPage();
            }
        });
        searchJobButton = (Button) findViewById(R.id.SearchJobButton);
        searchJobButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SJPage();
            }
        });
    }


    public void initializeFirebase(){
        this.db = FirebaseDatabase.getInstance().getReference();
        Log.d("TAG1", "after init");
    }

    public void addToDatabase(Job job) {
        db.child(String.valueOf(key)).setValue(job);
        key++;
        Log.d("TAG1", String.valueOf(key));
        Log.d("TAG1", "new job added: " +job.getJobTitle());
    }

    public void synchronizeDatabase(DataSnapshot snapshot){
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
    }

    public List<Job> getJobsInDatabase(){
        return jobsInDatabase;
    }


    public void wipeDatabase(){
        db.removeValue();
        Log.d("TAG1", "data wiped");
    }

    public void goToCJPage(){
        Intent create = new Intent(getApplicationContext(), CreateJob.class);
        startActivity(create);
    }

    public void SJPage() {
        Intent search = new Intent(getApplicationContext(), SearchJob.class);
        startActivity(search);
    }

}