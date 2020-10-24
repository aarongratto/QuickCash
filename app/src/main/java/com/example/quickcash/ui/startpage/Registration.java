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
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.IdlingResource;

import com.example.quickcash.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    private FirebaseAuth fbAuth;

    private EditText emailText;
    private EditText passwordText;
    private TextView statusMessageLabel;
    private Button registerButton;
    private Button loginButton;

    @VisibleForTesting
    private CountingIdlingResource idlingResource;  // Idle resource for testing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        getUIElements();

        idlingResource = new CountingIdlingResource("FirebaseCalls");
        fbAuth = FirebaseAuth.getInstance();
    }

    private void getUIElements() {
        emailText = (EditText) findViewById(R.id.loginEmailText);
        passwordText = (EditText) findViewById(R.id.loginPasswordText);
        statusMessageLabel = (TextView) findViewById(R.id.loginStatusMessage);
        registerButton = (Button) findViewById(R.id.registrationLoginButton);
        loginButton = (Button) findViewById(R.id.registrationLoginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });
    }

    private void register() {
        idlingResource.increment();

        fbAuth.createUserWithEmailAndPassword(emailText.getText().toString(),
                passwordText.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            statusMessageLabel.setText("Registration success, as "
                                    + fbAuth.getCurrentUser().getEmail());
                        }
                        else {
                            statusMessageLabel.setText("Registration failed");
                        }
                        idlingResource.decrement();
                    }
                });
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public IdlingResource getIdleResource() {
        return idlingResource;
    }
}
