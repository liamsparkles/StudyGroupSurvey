package com.groupsurvey.surveymanager.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String firstName;
    private String lastName;

    public Employee(int myId, String fName, String lName) {
       this.id = myId;
       this.firstName = fName;
       this.lastName = lName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int myId) {
        this.id = myId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.firstName + " " + this.lastName;
    }

}
