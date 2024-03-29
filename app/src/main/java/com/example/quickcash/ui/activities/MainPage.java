package com.example.quickcash.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.job.Job;
import com.example.quickcash.job.JobDatabase;
import com.example.quickcash.R;

import java.util.ArrayList;
import java.util.List;


public class MainPage extends AppCompatActivity {

    private Button createJobButton;
    private Button searchJobButton;
    private Button logoutButton;
    private Button jobStatusButton;
    // TODO: Remove temp button (ON XML FILE AS WELL) once Job class has a payment button
    // private Button jobPayButton;

    JobDatabase jDB;
    private static List<Job> jobsInProgress = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getUIElements();

        jDB = new JobDatabase();
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
        jobStatusButton = (Button) findViewById(R.id.jobCreateJobButton3);
        jobStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobsInProgress = jDB.getJobsInDatabase();
                goToPaymentStatusPage();
            }
        });

/*
        // TODO: Remove this temporary button
        jobPayButton = findViewById(R.id.jobPayButton);
        jobPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payIntent = new Intent(getApplicationContext(), Payment.class);
                payIntent.putExtra("Amount", "30.30");
                startActivity(payIntent);
            }
        });

 */

        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){
                deleteLoginInfo();
                goToLoginPage();
            }
        });
    }


    public void goToCJPage(){
        Intent create = new Intent(getApplicationContext(), CreateJob.class);
        startActivity(create);
    }

    public void SJPage() {
        Intent search = new Intent(getApplicationContext(), SearchJob.class);
        startActivity(search);
    }

    public void goToLoginPage(){
        Intent search = new Intent(getApplicationContext(), Login.class);
        startActivity(search);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void deleteLoginInfo(){
        deleteSharedPreferences("pref_data");
    }
    public void goToPaymentStatusPage(){
        Intent paymentStatus = new Intent(getApplicationContext(), PaymentStatus.class);
        startActivity(paymentStatus);
    }

    public static List<Job> getJobsInProgress(){return jobsInProgress;}
}