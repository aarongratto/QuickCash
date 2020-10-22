package com.example.quickcash.ui.startpage;


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
    private Button login;
    private Button signup;

    @VisibleForTesting
    private CountingIdlingResource idlingResource;  // Idle resource for testing


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        login = (Button) findViewById(R.id.buttonLogin);
        signup = (Button) findViewById(R.id.buttonSignUp);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openSignupPage();
            }
        });

        // Get UI elements
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        labelStatusMessage = findViewById(R.id.labelStatusMessage);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        idlingResource = new CountingIdlingResource("FirebaseCalls");

        fbAuth = FirebaseAuth.getInstance();
    }

    public void openSignupPage(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    protected void login() {
        idlingResource.increment();

        fbAuth.signInWithEmailAndPassword(textEmail.getText().toString(),
                textPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
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


