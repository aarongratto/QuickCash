package com.example.quickcash.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.Job.Hiring;
import com.example.quickcash.Job.Job;
import com.example.quickcash.JobDatabase;
import com.example.quickcash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Takumi Kariya
 * Date: 2020-11-29
 *
 * Create Job class initiates creation using UI elements
 */



import java.util.ArrayList;

public class CreateJob extends AppCompatActivity {
    JobDatabase jDB;
    List<Job> jobsInDatabase = new ArrayList<>();

    private Spinner CJLocationSpinner;
    private Spinner CJJobtypeSpinner;
    private EditText CJTitle;
    private EditText CJDescription;
    private EditText CJWage;
    private Button CJButtonToCreate;
    private Button CJButtonToPreferences;
    private ImageView CJButtonToMain;
    private ArrayAdapter<String> CJAdapterLocation;
    private ArrayAdapter<String> CJAdapterJobtype;

    String[] preferenceList;
    boolean[] checkedList;
    ArrayList<String> preferencesToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);
        CJgetUIElements();

        jDB = new JobDatabase();
    }

    private void CJgetUIElements(){
        CJLocationSpinner = (Spinner) findViewById(R.id.CJ_location_spinner);
        CJJobtypeSpinner = (Spinner) findViewById(R.id.CJ_jobtype_spinner);
        CJTitle = (EditText) findViewById(R.id.CJ_task_title_enter);
        CJDescription = (EditText) findViewById(R.id.CJ_task_description_enter);
        CJWage = (EditText) findViewById(R.id.CJ_payment_enter);
        CJButtonToCreate = (Button) findViewById(R.id.CJ_create_button);
        CJButtonToMain = (ImageView) findViewById(R.id.CJ_to_main);

        preferenceList = getResources().getStringArray(R.array.default_preferences);
        preferencesToAdd = new ArrayList<>();
        checkedList = new boolean[preferenceList.length];
        CJButtonToPreferences = (Button) findViewById(R.id.CJ_preferences_button);

        // Add/Remove preferences from the list
        CJButtonToPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder preferenceBuilder = new AlertDialog.Builder(CreateJob.this);
                preferenceBuilder.setTitle("Preferences");
                preferenceBuilder.setMultiChoiceItems(preferenceList, checkedList, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        if(isChecked && !preferencesToAdd.contains(preferenceList[i])) {
                            preferencesToAdd.add(preferenceList[i]);
                        }
                        if (isChecked && preferencesToAdd.contains(preferenceList[i])) {
                            preferencesToAdd.remove(preferenceList[i]);
                        }
                    }
                });
            }
        });

        CJAdapter = new ArrayAdapter<String>(CreateJob.this,
        CJAdapterLocation = new ArrayAdapter<String>(CreateJob.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        CJAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CJLocationSpinner.setAdapter(CJAdapterLocation);

        CJAdapterJobtype = new ArrayAdapter<String>(CreateJob.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.job_type));
        CJAdapterJobtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CJJobtypeSpinner.setAdapter(CJAdapterJobtype);

        CJButtonToMain.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v){
                        toMainPage();
                    }
                }
        );

        //need to be modified to search job on server
        CJButtonToCreate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addToDatabase();
                    }
                }
        );

    }

    private void toMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
        finish();
    }

    public void addToDatabase() {
        String jTitle = CJTitle.getText().toString();
        String jLocation = CJLocationSpinner.getSelectedItem().toString();
        String jDesc = CJDescription.getText().toString();
        double jWage = Double.parseDouble(CJWage.getText().toString());

        Hiring job = new Hiring(jTitle, jLocation, jDesc, jWage);
        jDB.addToDatabase(job);
    }


    public List<Job> getJobsInDatabase(){
        return jobsInDatabase;
    }

    public void wipeDatabase(){
        jDB.wipeDatabase();
        Log.d("TAG1", "data wiped");
    }
}
