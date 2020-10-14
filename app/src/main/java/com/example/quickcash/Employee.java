package com.example.quickcash;

/**
 * Author: Patrick Strongman
 * Date: 2020-10-14
 *
 * Employee class representing an employee that can log into the app
 */

public class Employee extends User {

    @Override
    public void login(String username, String password) {
        // Fake the login until database is implemented
        if (username.equals("Patrick") && password.equals("password")) {
            this.username = username;
            loggedIn = true;
        }
    }

    @Override
    protected String setUserType() {
        return "employee";
    }
}
