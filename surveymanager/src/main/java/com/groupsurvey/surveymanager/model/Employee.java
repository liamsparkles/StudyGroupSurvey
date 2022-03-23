package com.groupsurvey.surveymanager.model;

public class Employee {
    // Stores data for the user's/employee name, used as the key to access the survey results
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
