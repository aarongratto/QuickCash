package com.example.quickcash;

public class Employer {
    private String username;
    private String userType = "employer";

    private boolean loggedIn = false;

    public void login(String username, String password) {
        // Fake the login until database is implemented
        if (username.equals("Aaron") && password.equals("pass")) {
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
