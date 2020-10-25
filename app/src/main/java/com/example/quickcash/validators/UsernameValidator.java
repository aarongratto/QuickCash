package com.example.quickcash.validators;

/**
 * Author: Aaron Gratto
 * Date: 2020-10-14
 *
 * UsernameValidator class validates that usernames fit the requirements
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    private String username;

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public UsernameValidator(String username){this.username = username;}

    public boolean validUser(){
        Pattern pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher matcher = pattern.matcher(username);
        boolean found = matcher.find();
        return !username.equals("") && !found;
    }
}
