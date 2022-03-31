package com.example.telemedizin.Model;

public class User {

    private String personalID, fullName, password;

    public User() {

    }

    public User(String personalID, String fullName, String password) {
        this.personalID = personalID;
        this.fullName = fullName;
        this.password = password;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
