package com.groupsurvey.surveymanager.service;

import com.groupsurvey.surveymanager.model.Question;
import com.groupsurvey.surveymanager.model.Survey;
import com.groupsurvey.surveymanager.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteSurvey(String firstName, String lastName) {
        surveyRepository.deleteByName(firstName, lastName);
    }

    public Float checkSurvey(String firstName, String lastName) {
        Survey mySurvey = surveyRepository.findByName(firstName, lastName).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Survey with name %s", firstName, lastName)));
        List<Question> myQuestions = questionService.getAllQuestions();
        String[] myResponses = mySurvey.getResponses();

        int total_questions = myQuestions.size();
        int correct_questions = 0;
        for (int i = 0; i < total_questions; i++) {
            if (myQuestions.get(i).isCorrect(myResponses[i])) {
                correct_questions += 1;
            }
        }
        return (float) correct_questions / total_questions;
    }


}