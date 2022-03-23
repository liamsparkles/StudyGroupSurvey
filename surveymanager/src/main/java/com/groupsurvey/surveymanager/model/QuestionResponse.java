package com.groupsurvey.surveymanager.model;

public class QuestionResponse {
    private int rId;
    private String rText;

    public QuestionResponse(int rId, String rText) {
        this.rId = rId;
        this.rText = rText;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getrText() {
        return rText;
    }

    public void setrText(String rText) {
        this.rText = rText;
    }

    @Override
    public String toString() {
        return this.rId + " " + this.rText;
    }
}
