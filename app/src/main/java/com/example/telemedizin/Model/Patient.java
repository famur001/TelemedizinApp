package com.example.telemedizin.Model;

public class Patient {

    private String vorname, nachname, geburtsdatum, barthelScore, personalID;

    public Patient() {

    }

    public Patient(String vorname, String nachname, String geburtsdatum, String barthelScore, String personalID) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.barthelScore = barthelScore;
        this.personalID = personalID;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getBarthelScore() {
        return barthelScore;
    }

    public void setBarthelScore(String barthelScore) {
        this.barthelScore = barthelScore;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }
}
