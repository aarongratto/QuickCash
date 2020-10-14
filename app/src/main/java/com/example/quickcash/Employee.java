package com.example.quickcash;

/**
 * Author: Patrick Strongman
 * Date: 2020-10-14
 *
 * Employee class representing an employee that can log into the app
 */

public class Employee {
    private String username;
    private String userType = "employee";

    private boolean loggedIn = false;

    public void login(String username, String password) {
        // Fake the login until database is implemented
        if (username.equals("Patrick") && password.equals("password")) {
            this.username = username;
            loggedIn = true;
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }
}
