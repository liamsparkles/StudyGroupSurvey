package com.groupsurvey.surveymanager.model;

public class SurveyResponse {
    private int qId; // id for the question
    private int rId; // id for the response

    public SurveyResponse(int qId, int rId) {
        this.qId = qId;
        this.rId = rId;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
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
