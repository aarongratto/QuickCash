package com.example.quickcash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private String email;

    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}

    public EmailValidator(String email){this.email = email;}

    public boolean validEmail(){
        Pattern pattern = Pattern.compile("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
