package com.groupsurvey.surveymanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Survey {
    // Stores the name and responses/answers for an employee
    @Id
    private Employee employee;
    private SurveyResponse[] surveyResponses;

    public Survey() {};

    public Survey(Employee employee, SurveyResponse[] surveyResponses) {
        this.employee = employee;
        this.surveyResponses = surveyResponses;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SurveyResponse[] getResponses() {
        return surveyResponses;
    }

    public void setResponses(SurveyResponse[] surveyResponses) {
        this.surveyResponses = surveyResponses;
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder(this.employee.toString()).append(" ");
        for (SurveyResponse response : this.surveyResponses) {
            myText.append(response).append(" ");
        }
        return myText.toString();
    }
}


