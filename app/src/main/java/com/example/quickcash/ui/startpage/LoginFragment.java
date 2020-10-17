package com.example.quickcash.ui.startpage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quickcash.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ){
        return inflater.inflate(R.layout.signin, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.signup).setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_signIn_to_registration);
            }

        });
    }
}


