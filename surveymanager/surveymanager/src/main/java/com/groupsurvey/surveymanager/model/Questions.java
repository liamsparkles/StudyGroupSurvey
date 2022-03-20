package com.groupsurvey.surveymanager.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Questions implements Serializable{
    private ArrayList<Question> questions;

    public Questions(ArrayList<Question> qs) {
        this.questions = qs;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> qs) {
        this.questions = qs;
    }

    public void addQuestion(Question q) {
        this.questions.add(q);
    }

    public void deleteQuestion(int id) {
        this.questions.remove(id);
    }

    public String getQuestionResponse(int iQuestion, int iResponse) {
        return this.questions.get(iQuestion).getResponse(iResponse);
    }

    public String[] surveyToResponses(Survey survey) {
        String[] responses = new String[survey.getResponses().length];
        for (int i = 0; i < survey.getResponses().length; i++) {
            responses[i] = this.getQuestionResponse(i, survey.getResponses()[i]);
        }
        return responses;
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder();
        for (Question que : this.questions) {
            myText.append(que.toString()).append("\n");
        }
        return myText.toString();
    }
}

class Question implements Serializable {
    private String questionName;
    private String[] responses;

    public Question(String qName, String[] qResponses) {
        this.questionName = qName;
        this.responses = qResponses;
    }

    public String getQuestionName() {
        return this.questionName;
    }

    public void setQuestionName(String qName) {
        this.questionName = qName;
    }

    public String[] getResponses() {
        return this.responses;
    }

    public String getResponse(int iResponse) {
        return this.responses[iResponse];
    }

    public void setResponses(String[] qResponses) {
        this.responses = qResponses;
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder(this.questionName).append("\n");
        for (String resp : this.responses) {
            myText.append(resp).append("\n");
        }
        return myText.toString();
    }
}
