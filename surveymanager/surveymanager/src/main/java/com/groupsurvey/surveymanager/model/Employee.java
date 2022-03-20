package com.groupsurvey.surveymanager.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;

    public Employee(String fName, String lName) {
       this.firstName = fName;
       this.lastName = lName;
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
        return this.firstName + " " + this.lastName;
    }

}
