package com.example.quickcash.ui.startpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickcash.R;

public class SignUp extends AppCompatActivity {
    private Button signup;
    private TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        signin = (TextView) findViewById(R.id.textView2);


        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openSigninPage();
            }
        });
    }

    public void openSigninPage(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
