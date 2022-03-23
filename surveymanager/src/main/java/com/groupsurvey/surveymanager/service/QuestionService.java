package com.groupsurvey.surveymanager.service;

import com.groupsurvey.surveymanager.model.Question;
import com.groupsurvey.surveymanager.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    //Service for the question database

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question) {
        questionRepository.insert(question);
    }

    public void updateQuestion(Question question) {
        //Find the question, then make the necessary changes
        Question changeQuestion = questionRepository.findById(Integer.toString(question.getQuestionId()))
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Question with Id %s", question.getQuestionId())));

        changeQuestion.setQuestionId(question.getQuestionId());
        changeQuestion.setQuestionText(question.getQuestionText());
        changeQuestion.setResponses(question.getResponses());

        questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(String id) {
        return questionRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find question with the id: %d", id)));
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(Integer.toString(id));
    }

}
