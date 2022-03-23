package com.groupsurvey.surveymanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Question {
    // Stores a question, including the question prompt/text, possible responses, and the true answer
    @Id
    @Field(name = "_id")
    private int questionId;
    @Field(name = "text")
    @Indexed(unique = true)
    private String questionText;
    private QuestionResponse[] responses;
    private int answer;

    public Question(int questionId, String questionText, QuestionResponse[] responses, int answer) {
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

    public QuestionResponse[] getResponses() {
        return this.responses;
    }

    public void setResponses(QuestionResponse[] responses) {
        this.responses = responses;
    }

    public int getAnswer() {
       return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isCorrect(SurveyResponse surveyResponse) {
        return surveyResponse.getrId() == this.answer;
    }

    @Override
    public String toString() {
        StringBuilder myText = new StringBuilder(this.questionId).append(" ").append(this.questionText);
        myText.append("\n");
        for (QuestionResponse resp : this.responses) {
            myText.append(resp.toString()).append("\n");
        }
        return myText.toString();
    }
}