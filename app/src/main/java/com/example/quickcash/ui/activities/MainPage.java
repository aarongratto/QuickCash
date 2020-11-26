package com.example.quickcash.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

    private Spinner searchingLocationSpinner;
    private Spinner addingLocationSpinner;
    private EditText jobTitleText;
    private EditText jobDescText;
    private EditText jobWageText;
    private Button createJobButton;
    private ArrayAdapter<String> myAdapter;


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
        searchingLocationSpinner = (Spinner) findViewById(R.id.jobSearchLocationSpinner);
        addingLocationSpinner = (Spinner) findViewById(R.id.jobAddLocationSpinner);
        createJobButton = (Button) findViewById(R.id.jobCreateJobButton);
        jobTitleText = (EditText) findViewById(R.id.jobAddTitleTextEdit);
        jobDescText = (EditText) findViewById(R.id.jobAddDescTextEdit);
        jobWageText = (EditText) findViewById(R.id.jobAddWageText);

        myAdapter = new ArrayAdapter<String>(MainPage.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchingLocationSpinner.setAdapter(myAdapter);
        addingLocationSpinner.setAdapter(myAdapter);
        createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goToCJPage();
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

   /* //temporally usage for testing UI
    public void goToCJPage(){
        Intent intent = new Intent(getApplicationContext(), CreateJob.class);
        startActivity(intent);
    }*/
}