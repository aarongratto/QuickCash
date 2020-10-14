package com.example.quickcash;

/**
 * Author: Patrick Strongman
 * Date: 2020-10-14
 *
 * Employer class representing an employer that can log into the app
 */

public class Employer extends User {

    @Override
    public void login(String username, String password) {
        // Fake the login until database is implemented
        if (username.equals("Aaron") && password.equals("pass")) {
            this.username = username;
            loggedIn = true;
        }
    }

    @Override
    protected String setUserType() {
        return "employer";
    }
}
