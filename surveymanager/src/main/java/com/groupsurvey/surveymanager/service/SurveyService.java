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
        surveyRepository.save(survey);
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
        surveyRepository.deleteByName(firstName, lastName);
    }

    public Float checkSurvey(String firstName, String lastName) {
        Survey mySurvey = surveyRepository.findByName(firstName, lastName).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Survey with name %s %s", firstName, lastName)));
        List<Question> myQuestions = questionService.getAllQuestions();
        SurveyResponse[] myResponses = mySurvey.getResponses();

        int total_questions = myQuestions.size();
        int correct_questions = 0;
        for (SurveyResponse surveyResponse : myResponses) {
            int surveyQId = surveyResponse.getqId();
            int icount = 0;
            int questionQId = -1;
            while (surveyQId != questionQId && icount < myQuestions.size()) {
                questionQId = myQuestions.get(icount++).getQuestionId();
            }
            if (surveyQId != questionQId) throw new RuntimeException(
                    String.format("Cannot find question with question id %d", surveyQId));
            if (myQuestions.get(icount-1).isCorrect(surveyResponse)) correct_questions++;
        }
        return (float) correct_questions / total_questions;
    }


}