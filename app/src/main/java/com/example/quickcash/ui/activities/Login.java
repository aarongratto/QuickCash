package com.example.quickcash.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.example.quickcash.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth fbAuth;

    private EditText textEmail;
    private EditText textPassword;
    private TextView labelStatusMessage;
    private Button buttonLogin;
    private Button buttonSignUp;
    boolean taskSuccess;

    @VisibleForTesting
    private CountingIdlingResource idlingResource;  // Idle resource for testing


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        buttonLogin = (Button) findViewById(R.id.loginLoginButton);
        buttonSignUp = (Button) findViewById(R.id.loginRegisterButton);

        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRegistrationPage();
            }
        });

        // Get UI elements
        textEmail = findViewById(R.id.loginEmailText);
        textPassword = findViewById(R.id.loginPasswordText);
        labelStatusMessage = findViewById(R.id.loginStatusMessage);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                if (taskSuccess){
                    openMainPage();
                }
            }
        });

        idlingResource = new CountingIdlingResource("FirebaseCalls");

        fbAuth = FirebaseAuth.getInstance();
    }

    public void openRegistrationPage(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        finish();
    }

    public void openMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
        finish();
    }

    protected void login() {
        idlingResource.increment();

        fbAuth.signInWithEmailAndPassword(textEmail.getText().toString(),
                textPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        taskSuccess = task.isSuccessful();
                        if (taskSuccess) {
                            labelStatusMessage.setText("Login success, as "
                                    + fbAuth.getCurrentUser().getEmail());
                        }
                        else {
                            labelStatusMessage.setText("Login failed");
                        }
                        idlingResource.decrement();
                    }
                });
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new CountingIdlingResource("FirebaseCalls");
        }
        return idlingResource;
    }
}


