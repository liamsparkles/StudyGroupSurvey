package com.groupsurvey.surveymanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document
public class Survey implements Serializable {
    @Id
    private Employee employee;
    private String[] responses;

    public Survey() {};

    public Survey(Employee employee, String[] responses){
        this.employee = employee;
        this.responses = responses;
    }

    public String[] getResponses() {
        return this.responses;
    }

    public void setResponses(String[] responses) {
        this.responses = responses;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder(this.employee.toString()).append(" ");
        for (String response : this.responses) {
            myText.append(response).append(" ");
        }
        return myText.toString();
    }
}


