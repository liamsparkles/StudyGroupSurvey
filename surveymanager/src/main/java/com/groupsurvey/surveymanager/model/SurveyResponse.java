package com.groupsurvey.surveymanager.model;

public class SurveyResponse {
    // A response for a question
    private String qId; // id for the question
    private int rId; // id for the response

    public SurveyResponse(String qId, int rId) {
        this.qId = qId;
        this.rId = rId;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        return this.qId + " " + this.rId;
    }
}
