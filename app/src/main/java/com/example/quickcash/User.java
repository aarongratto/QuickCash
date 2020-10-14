package com.example.quickcash;

public abstract class User {
    protected String username;
    private String userType;

    protected boolean loggedIn = false;

    public User() {
        userType = setUserType();
    }

    public abstract void login(String username, String password);
    protected abstract String setUserType(); // Force subclass to instantiate userType

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
