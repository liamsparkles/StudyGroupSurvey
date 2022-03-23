package com.groupsurvey.surveymanager.restcontroller;

import com.groupsurvey.surveymanager.model.Question;
import com.groupsurvey.surveymanager.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    //Controller to handle requests to the question database

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        System.out.println(id);
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
