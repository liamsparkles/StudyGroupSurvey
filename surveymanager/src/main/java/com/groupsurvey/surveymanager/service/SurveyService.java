package com.groupsurvey.surveymanager.service;

import com.groupsurvey.surveymanager.model.Question;
import com.groupsurvey.surveymanager.model.Survey;
import com.groupsurvey.surveymanager.model.SurveyResponse;
import com.groupsurvey.surveymanager.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    //Service for the survey database

    private final SurveyRepository surveyRepository;
    private final QuestionService questionService;

    public SurveyService(SurveyRepository surveyRepository, QuestionService questionService) {
        this.surveyRepository = surveyRepository;
        this.questionService = questionService;
    }

    public void addSurvey(Survey survey) {
        surveyRepository.insert(survey);
    }

    public void updateSurveyResponses(Survey survey) {
        Survey changeSurvey = surveyRepository.findByName(survey.getEmployee().getFirstName(),
                        survey.getEmployee().getLastName())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Survey with name %s %s",
                               survey.getEmployee().getFirstName(), survey.getEmployee().getLastName())));

        changeSurvey.setResponses(survey.getResponses());
        surveyRepository.save(changeSurvey);
    }

    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public Survey getSurveyByName(String firstName, String lastName) {
        return surveyRepository.findByName(firstName, lastName).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Survey with name %s", firstName, lastName)));
    }

    public boolean getSurveyExistance(String firstName, String lastName) {
        Optional<Survey> tempS = surveyRepository.findByName(firstName, lastName);
        return tempS.isPresent();
    }

    public void deleteSurvey(String firstName, String lastName) {
        Survey mySurvey = surveyRepository.findByName(firstName, lastName).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Survey with name %s %s", firstName, lastName)));
        surveyRepository.delete(mySurvey);
    }

    public Float checkSurvey(String firstName, String lastName) {
        // Finds the survey using the passed name, then calculates the amount of correct questions against the
        // question database.
        Survey mySurvey = surveyRepository.findByName(firstName, lastName).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Survey with name %s %s", firstName, lastName)));
        List<Question> myQuestions = questionService.getAllQuestions();
        SurveyResponse[] myResponses = mySurvey.getResponses();

        int total_questions = myQuestions.size();
        int correct_questions = 0;
        for (SurveyResponse surveyResponse : myResponses) {
            String surveyQId = surveyResponse.getqId();
            int icount = 0;
            String questionQId = "";
            while (!surveyQId.equals(questionQId) && icount < myQuestions.size()) {
                questionQId = myQuestions.get(icount++).getQuestionId();
            }
            if (!surveyQId.equals(questionQId)) throw new RuntimeException(
                    String.format("Cannot find question with question id %s", surveyQId));
            if (myQuestions.get(icount-1).isCorrect(surveyResponse)) correct_questions++;
        }
        return (float) correct_questions / total_questions;
    }


}