package com.groupsurvey.surveymanager.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName) {
       this.firstName = firstName;
       this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

}
