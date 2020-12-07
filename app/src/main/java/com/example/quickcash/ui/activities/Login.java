package com.example.quickcash.ui.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.example.quickcash.password.Encryptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.example.quickcash.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

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
            }
        });

        idlingResource = new CountingIdlingResource("FirebaseCalls");

        fbAuth = FirebaseAuth.getInstance();

        getPrevLogin();
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

    public void getUserData(){
        // when success login

        //  read "pref_data"
        SharedPreferences prefData = getSharedPreferences("pref_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefData.edit();

        // email and password
        editor.putString("email", textEmail.getText().toString());
        editor.putString("pass", textPassword.getText().toString());

        // save
        editor.commit();
    }

    public void getPrevLogin() {
        // after show login page

        // read "pref_data"
        Key key = Encryptor.generateKey();
        SharedPreferences prefData = getSharedPreferences("pref_data", MODE_PRIVATE);
        String email = prefData.getString("email", "");
        String pass = prefData.getString("pass", "");

        // empty check
        if (email != null && email.length() > 0) {
            // enter email
            this.textEmail.setText(email);
        }
        if (pass != null && pass.length() > 0) {
            // enter password
            this.textPassword.setText(pass);
        }
    }

    protected void login() {
        if (textEmail.getText().toString().isEmpty()|| textPassword.getText().toString().isEmpty()) {
            labelStatusMessage.setText("Please enter both an email and password");
            return;
        }

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

                            getUserData();
                            openMainPage();
                        } else {
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


