package com.example.quickcash.ui.startpage;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickcash.R;

public class Login extends AppCompatActivity {
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openSignupPage();
            }
        });
    }

    public void openSignupPage(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}


