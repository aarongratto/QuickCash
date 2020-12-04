package com.example.quickcash.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.R;

public class SearchJob extends AppCompatActivity {

    private Spinner SJLocationSpinner;
    private SearchView SJTitle;
    private Button SJButtonToSearch;
    private ImageView SJButtonToMain;
    private ArrayAdapter<String> SJAdapter;

    String titlePreference;
    String locationPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_search);
        SJgetUIElements();
    }


    private void SJgetUIElements(){
        SJLocationSpinner = (Spinner) findViewById(R.id.jobSearchLocationSpinner);
        SJTitle = (SearchView) findViewById(R.id.jobSearchBar);
        SJButtonToSearch = (Button) findViewById(R.id.SearchJobButton);
        SJButtonToMain = (ImageView) findViewById(R.id.jobbackMain);

        SJAdapter = new ArrayAdapter<String>(SearchJob.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        SJAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SJLocationSpinner.setAdapter(SJAdapter);

        SJButtonToMain.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v){
                        toMainPage();
                    }
                }
        );

        //need to be modified to send job to server
        SJButtonToSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveSearchPreferences();
                        toMapPage();
                    }
                }
        );

    }

    private void toMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
        finish();
    }

    private void toMapPage(){
        Intent intent = new Intent(getApplicationContext(), MapMain.class);
        startActivity(intent);
        finish();
    }

    private void saveSearchPreferences(){
        titlePreference = SJTitle.toString();
        locationPreference = SJLocationSpinner.getSelectedItem().toString();
    }

    public String getTitlePreference(){
        return titlePreference;
    }
    public String getLocationPreference(){
        return locationPreference;
    }
}
