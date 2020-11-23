package com.example.quickcash;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.quickcash.Job.Hiring;
import com.example.quickcash.Job.Job;
import com.example.quickcash.Job.LookingForWork;
import com.example.quickcash.ui.activities.MainPage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})
public class JobDatabaseTest {
    //create two new 'Hiring' job objects
    Hiring h1 = new Hiring("Chef", "Truro", "We are looking to hire a new chef", 15.20);
    Hiring h2 = new Hiring("Software Engineer", "Halifax", "We are looking to hire a new Software Engineer", 20.56);
    LookingForWork l1 = new LookingForWork("Labor", "Antigonish", "I am looking for a labor job", 12.55);
    LookingForWork l2 = new LookingForWork("Volunteer", "Sydney", "I am looking for a volunteer position", 0.00);
    List<Job> jobsInDatabase = new ArrayList<>();

    private DatabaseReference dbRefMock;


    //setup mock database
    @Before
    public void before(){
        dbRefMock = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase  dbMock = Mockito.mock(FirebaseDatabase.class);
        when(dbMock.getReference()).thenReturn(dbRefMock);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(dbMock);
    }

    @Test
    public void readAndWriteToDatabase(){
        when(dbRefMock.child(anyString())).thenReturn(dbRefMock);
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child(String.valueOf(0)).setValue(h1);
        db.child(String.valueOf(0)).setValue(h2);
        db.child(String.valueOf(0)).setValue(l1);
        db.child(String.valueOf(0)).setValue(l2);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                while (it.hasNext()){
                    DataSnapshot jobSnapshot = it.next();
                    String currentJob = jobSnapshot.getValue().toString();

                    if (currentJob.endsWith("jobType=Hiring}")){
                        Hiring hiringJob = jobSnapshot.getValue(Hiring.class);
                        jobsInDatabase.add(hiringJob);
                        assertTrue(hiringJob.getJobTitle().equals("Chef") || hiringJob.getJobTitle().equals("Software Engineer"));
                        assertTrue(hiringJob.getJobLocation().equals("Truro") || hiringJob.getJobLocation().equals("Sydney"));
                        assertTrue(hiringJob.getJobWage() == 15.20 || hiringJob.getJobWage() == 20.56);

                    }
                    else {
                        LookingForWork lookingJob = jobSnapshot.getValue(LookingForWork.class);
                        jobsInDatabase.add(lookingJob);
                        assertTrue(lookingJob.getJobTitle().equals("Labor") || lookingJob.getJobTitle().equals("Volunteer"));
                        assertTrue(lookingJob.getJobLocation().equals("Antigonish") || lookingJob.getJobLocation().equals("Halifax"));
                        assertTrue(lookingJob.getJobWage() == 12.55 || lookingJob.getJobWage() == 0.00);
                    }
                }
                assertTrue(jobsInDatabase.size() == 4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}