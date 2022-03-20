package com.groupsurvey.surveymanager.model;

import java.io.Serializable;

public class Survey implements Serializable {
    private Employee employee;
    private int[] responses;

    public Survey() {};

    public Survey(Employee empl, int[] surveyResponses){
        this.employee = empl;
        this.responses = surveyResponses;
    }

    public int[] getResponses() {
        return this.responses;
    }

    public void setResponses(int[] surveyResponses) {
        this.responses = surveyResponses;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee empl) {
        this.employee = empl;
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder(this.employee.toString()).append(" ");
        for (int response : this.responses) {
            myText.append(response).append(" ");
        }
        return myText.toString();
    }
}


