package com.example.quickcash.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.R;
/**
 * Author: Takumi Kariya
 * Date: 2020-11-29
 *
 * Create Job class initiates creation using UI elements
 */
public class CreateJob extends AppCompatActivity {

    private Spinner CJLocationSpinner;
    private EditText CJTitle;
    private EditText CJDescription;
    private EditText CJWage;
    private Button CJButtonToCreate;
    private ImageView CJButtonToMain;
    private ArrayAdapter<String> CJAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);
        CJgetUIElements();
    }

    private void CJgetUIElements(){
        CJLocationSpinner = (Spinner) findViewById(R.id.CJ_location_spinner);
        CJTitle = (EditText) findViewById(R.id.CJ_addTitle);
        CJDescription = (EditText) findViewById(R.id.CJ_addDescription);
        CJWage = (EditText) findViewById(R.id.CJ_addWage);
        CJButtonToCreate = (Button) findViewById(R.id.CJ_create_button);
        CJButtonToMain = (ImageView) findViewById(R.id.CJ_to_main);

        CJAdapter = new ArrayAdapter<String>(CreateJob.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        CJAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CJLocationSpinner.setAdapter(CJAdapter);

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

                    }
                }
        );

    }

    private void toMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
        finish();
    }

}
