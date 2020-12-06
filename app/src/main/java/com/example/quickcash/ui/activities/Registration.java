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
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.IdlingResource;

import com.example.quickcash.validators.EmailValidator;
import com.example.quickcash.validators.PasswordValidator;
import com.example.quickcash.R;
import com.example.quickcash.validators.UsernameValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    private FirebaseAuth fbAuth;

    private EditText emailText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private TextView statusMessageLabel;
    private Button registerButton;
    private Button loginButton;
    boolean taskSuccess;

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
        emailText = (EditText) findViewById(R.id.registrationEmailText);
        usernameText = (EditText) findViewById(R.id.registrationUsernameText);
        passwordText = (EditText) findViewById(R.id.registrationPasswordText);
        confirmPasswordText = (EditText) findViewById(R.id.registrationConfirmPasswordText);
        statusMessageLabel = (TextView) findViewById(R.id.registrationStatusMessage);
        registerButton = (Button) findViewById(R.id.registrationRegisterButton);
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
        if (validateInput()) {
            idlingResource.increment();

            fbAuth.createUserWithEmailAndPassword(emailText.getText().toString(),
                    passwordText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            taskSuccess = task.isSuccessful();
                            if (task.isSuccessful()) {
                                statusMessageLabel.setText("Registration success");
                                openMainPage();
                            } else {
                                statusMessageLabel.setText("Registration failed");
                            }
                            idlingResource.decrement();
                        }
                    });
        }

    }

    private boolean validateInput() {
        EmailValidator emailValidator
                = new EmailValidator(emailText.getText().toString());
        UsernameValidator usernameValidator
                = new UsernameValidator(usernameText.getText().toString());
        PasswordValidator passwordValidator
                = new PasswordValidator(passwordText.getText().toString());

        if (!emailValidator.validEmail()) {
            statusMessageLabel.setText("Invalid email");
            return false;
        }
        if (!usernameValidator.validUser()) {
            statusMessageLabel.setText("Invalid username");
            return false;
        }
        if (!passwordValidator.validPassword()) {
            statusMessageLabel.setText("Invalid password");
            return false;
        }
        if (!passwordText.getText().toString().equals(confirmPasswordText.getText().toString())) {
            statusMessageLabel.setText("Passwords don't match");
            return false;
        }

        return true;
    }

    public void openLoginPage(){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }

    public void openMainPage(){
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
    }

    public IdlingResource getIdleResource() {
        return idlingResource;
    }

}
