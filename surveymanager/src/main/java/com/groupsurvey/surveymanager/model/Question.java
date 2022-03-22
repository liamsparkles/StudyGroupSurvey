package com.groupsurvey.surveymanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Document
public class Question {
    @Id
    @Field(name = "_id")
    private int questionId;
    @Field(name = "text")
    @Indexed(unique = true)
    private String questionText;
    private String[] responses;
    private String answer;

    public Question(int questionId, String questionText, String[] responses, String answer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.responses = responses;
        this.answer = answer;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getResponses() {
        return this.responses;
    }

    public void setResponses(String[] responses) {
        this.responses = responses;
    }

    public String getAnswer() {
       return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect(String response) {
        return this.answer.equals(response);
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder(this.questionId).append(" ").append(this.questionText);
        myText.append("\n");
        for (String resp : this.responses) {
            myText.append(resp).append("\n");
        }
        return myText.toString();
    }
}

//class Questions{
//    private ArrayList<Question> questions;
//
//    public Questions(ArrayList<Question> qs) {
//        this.questions = qs;
//    }
//
//    public ArrayList<Question> getQuestions() {
//        return this.questions;
//    }
//
//    public void setQuestions(ArrayList<Question> qs) {
//        this.questions = qs;
//    }
//
//    public void addQuestion(Question q) {
//        this.questions.add(q);
//    }
//
//    public void deleteQuestion(int id) {
//        this.questions.remove(id);
//    }
//
//    public String getQuestionResponse(int iQuestion, String response) {
//        return this.questions.get(iQuestion).getResponse(response);
//    }
//
//    public String[] surveyToResponses(Survey survey) {
//        String[] responses = new String[survey.getResponses().length];
//        for (int i = 0; i < survey.getResponses().length; i++) {
//            responses[i] = this.getQuestionResponse(i, survey.getResponses()[i]);
//        }
//        return responses;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder myText = new StringBuilder();
//        for (Question que : this.questions) {
//            myText.append(que.toString()).append("\n");
//        }
//        return myText.toString();
//    }
//}
