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
import com.example.quickcash.payment.Payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainPage extends AppCompatActivity {

    private Button createJobButton;
    private Button searchJobButton;
    private Button jobStatusButton;
    // TODO: Remove temp button (ON XML FILE AS WELL) once Job class has a payment button
    private Button jobPayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getUIElements();

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
                goToPaymentStatusPage();
            }
        });


        // TODO: Remove this temporary button
        /*jobPayButton = findViewById(R.id.jobPayButton);
        jobPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payIntent = new Intent(getApplicationContext(), Payment.class);
                payIntent.putExtra("Amount", "30.30");
                startActivity(payIntent);
            }
        });*/
    }


    public void goToCJPage(){
        Intent create = new Intent(getApplicationContext(), CreateJob.class);
        startActivity(create);
    }

    public void SJPage() {
        Intent search = new Intent(getApplicationContext(), SearchJob.class);
        startActivity(search);
    }

    public void goToPaymentStatusPage(){
        Intent paymentStatus = new Intent(getApplicationContext(), PaymentStatus.class);
        startActivity(paymentStatus);
    }

}